package com.limepay.backenddevchallenge;

import com.limepay.backenddevchallenge.dto.Movie;
import com.limepay.backenddevchallenge.dto.MovieRecord;
import com.limepay.backenddevchallenge.service.DirectorService;
import com.limepay.backenddevchallenge.service.DirectorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(classes = DirectorService.class)
@ActiveProfiles("test")
public class DirectorServiceTest {
    @Value("${limepay.movies.url}")
    private String MOVIES_URL = "https://wiremock.dev.eroninternational.com/api/movies/search?page=1";

    @Mock
    private DirectorService service;


    @Test
    void getDirectorsTest(){
        List<String> actors = Arrays.stream((new String[]{"Owen Wilson", "Rachel McAdams", "Kathy Bates"})).toList();
        Movie movie1 = new Movie("Midnight in Paris","2011","PG-13","10 Jun 2011",
                "94 min","Comedy, Fantasy, Romance","Woody Allen","Woody Allen",actors);
        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);

        MovieRecord record = new MovieRecord(2, 10, 26, 3, movies);

        List<String> directors = new ArrayList<>();
        directors.add("Woody Allen");
        directors.add("Martin Scorsese");
        when(this.service.getDirectors(2)).thenReturn(directors);
        MovieRecord expectation = RestClient.create().get()
                .uri(MOVIES_URL, 2)
                .retrieve()
                .body(MovieRecord.class);
        assertThat(expectation).isEqualTo(record);
    }
}
