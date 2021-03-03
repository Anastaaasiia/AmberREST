import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


/**
 *
 * @author Anastasiia Bondarenko
 */
public class testConfig {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    static final String URI = "http://localhost";
    static final String BASE_PATH = "/api/rest/v1/";
    static final int PORT = 9999;

    public testConfig() {
    }


    @BeforeClass
    public void setUpClass() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(URI)
                .setBasePath(BASE_PATH)
                .setPort(PORT)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectBody("$", hasKey("id"))
                .expectBody("$", hasKey("name"))
                .expectBody("$", hasKey("organization"))
                .expectBody("$", hasKey("period"))
                .expectBody("$", hasKey("trade"))
                .expectContentType(ContentType.JSON)
                .build();


        requestSpecification = requestSpec;
    }

    public int availableId(){
        List<Integer> availableIds = given().when()
                .get("certificates/all")
                .then().extract().jsonPath()
                .getList("id");

        Random rand = new Random();
        return availableIds.get(rand.nextInt(availableIds.size()));
    }

}
