import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.limepay.backenddevchallenge.controller.MainController;
import com.limepay.backenddevchallenge.service.DirectorService;
import com.limepay.backenddevchallenge.service.DirectorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class MainControllerTest {

    MockMvc mockMvc;

    @Mock
    DirectorService service;

    @InjectMocks
    MainController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string("Hi Limepay!"));
    }


   @Test
   void testGetDirectors() throws Exception {
        List<String> directors = new ArrayList<>();
        directors.add("Quentin Tarantino");
        when(service.getDirectors(2)).thenReturn(directors);
        this.mockMvc.perform(get("/api/directors?threshold=2")).andDo(print()).andExpect(status().isOk())
         .andExpect(content().string(containsString("Quentin Tarantino")));
   }

   @Configuration
   @Import(DirectorServiceImpl.class)
   static class Config{

   }
}
