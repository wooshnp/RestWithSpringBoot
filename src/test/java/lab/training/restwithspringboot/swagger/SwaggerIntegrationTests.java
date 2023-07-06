package lab.training.restwithspringboot.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lab.training.restwithspringboot.configs.TestConfigs;
import lab.training.restwithspringboot.testcontainers.AbstractIntegrationTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTests extends AbstractIntegrationTest {

    @Test
    public void shouldDisplaySwaggerUiPage() {
        var content =
                given()
                        .basePath("/swagger-ui.html")
                        .port(TestConfigs.SERVER_PORT)
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().asString();

        assertTrue(content.contains("Swagger UI"));

    }

}
