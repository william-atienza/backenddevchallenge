package com.limepay.backenddevchallenge.service;

import com.limepay.backenddevchallenge.dto.Movie;
import com.limepay.backenddevchallenge.dto.MovieRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class DirectorService {
    private Map<String, Set<String>> tempMap = new HashMap<>();

    @Cacheable(value = "directorsCache", key = "#threshold")
    public List<String> getDirectors(int threshold){
        List<Movie> movies = null;
        MovieRecord firstRecord = getMovies(1);
        movies = new ArrayList<>(firstRecord.data());
        log.debug("first record: {}", firstRecord);
        int page = firstRecord.total_pages();
        log.debug("totalPages: {}", page);
        while(page > 1){
            var record = getMovies(page);
            log.debug("record: {}", record);
            movies.addAll(record.data());
            page--;
        }

        mapDirectorAndMovies(movies);
        log.debug("tempMap: {}", tempMap);
        List<String> directors = new ArrayList<>();
        for(Map.Entry<String, Set<String>> set: tempMap.entrySet()){
            log.debug("key :{}, value:{}", set.getKey(), set.getValue());
            if(set.getValue().size() == threshold){
                directors.add(set.getKey());
            }
        }
        return directors;
    }

    MovieRecord getMovies(final int page){
        log.debug("getting page#: {}", page);
        return RestClient.create().get()
                .uri("https://wiremock.dev.eroninternational.com/api/movies/search?page={page}", page)
                .retrieve()
                .body(MovieRecord.class);
    }

    void mapDirectorAndMovies(List<Movie> movies){
        for(Movie movie: movies){
            Set<String> set = tempMap.get(movie.director());
            if(set != null){
                set.add(movie.title());
            }else{
                Set<String> newSet = new HashSet();
                newSet.add(movie.title());
                tempMap.put(movie.director(), newSet);
            }
        }
    }
}
