package com.limepay.backenddevchallenge.service;

import com.limepay.backenddevchallenge.dto.Movie;
import com.limepay.backenddevchallenge.dto.MovieRecord;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.*;

@Service
public class DirectorServiceImpl implements DirectorService {
    Logger log = LoggerFactory.getLogger(DirectorServiceImpl.class);

    @Value("${limepay.movies.url}")
    private String MOVIES_URL;

    private Map<String, Set<String>> directorAndMovies = new HashMap<>();

    @Cacheable(value = "directorsCache", key = "#threshold")
    public List<String> getDirectors(int threshold){
        MovieRecord firstRecord = getMovies(1);
        List<Movie> movies = new ArrayList<>(firstRecord.data());
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
        log.debug("directorAndMovies: {}", directorAndMovies);
        List<String> directors = new ArrayList<>();
        for(Map.Entry<String, Set<String>> set: directorAndMovies.entrySet()){
            log.debug("key :{}, value:{}", set.getKey(), set.getValue());
            if(set.getValue().size() > threshold){
                directors.add(set.getKey());
            }
        }
        Collections.sort(directors);
        return directors;
    }

    MovieRecord getMovies(final int page){
        log.debug("getting page#: {}", page);
        return RestClient.create().get()
                .uri(MOVIES_URL, page)
                .retrieve()
                .body(MovieRecord.class);
    }

    void mapDirectorAndMovies(List<Movie> movies){
        for(Movie movie: movies){
            Set<String> set = directorAndMovies.get(movie.director());
            if(set != null){
                set.add(movie.title());
            }else{
                Set<String> newSet = new HashSet();
                newSet.add(movie.title());
                directorAndMovies.put(movie.director(), newSet);
            }
        }
    }
}
