import com.limepay.backenddevchallenge.controller.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MainControllerTest {
    @Autowired
    MainController controller;

    @Test
    void contextLoads() throws Exception{
        assertThat(controller).isNull();
    }

    @Test
    void getDirectorsTest(){
//        WebTestClient client = MockMvc .bindToController(new MainController()).build();
//        client.get().uri("/api/directors?threshold=1")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }
}
