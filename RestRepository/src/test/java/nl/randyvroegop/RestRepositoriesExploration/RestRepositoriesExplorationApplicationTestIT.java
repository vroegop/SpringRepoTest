package nl.randyvroegop.RestRepositoriesExploration;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestRepositoriesExplorationApplicationTestIT {
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void
    lotto_resource_returns_200_with_expected_id_and_winners() {
        // Check array size
        given().basePath("/users").get("").then().statusCode(200).body("_embedded.users.size()", equalTo(0));

        // Check actual parameter value (if parameter page.size was a string, 20 would be replaced with that string)
        given().basePath("/users").get("").then().statusCode(200).body("page.size", equalTo(20));
    }

}