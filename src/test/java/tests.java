import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

/**
 *
 * @author Anastasiia Bondarenko
 */
public class tests extends testConfig {

    public tests() {
    }

    @Test(priority = 1)
    public void getCertificate_queryParams() {
        given().when()
                .queryParam("id", availableId())
                .get("certificate")
                .then()
                .assertThat()
                .statusCode(200)
                .spec(responseSpec)
                .time(lessThan(4000L));
 }

    @Test(priority = 2)
    public void createCertificate() {
        String payload = "{\"name\":\"TEST ANALYST\",\"organization\":\"AmberTeam\", \"period\":\"BEZTERMINOWO\", \"trade\":\"IT\"}";

        given().when()
                .body(payload)
                .post("certificate")
                .then()
                .assertThat()
                .statusCode(201)
                .spec(responseSpec)
                .body("name", equalTo("TEST ANALYST"))
                .header("Date", notNullValue())
                .time(lessThan(4000L));
    }

    @Test(priority = 3)
    public void getCertificate_pathParams() {

        given().when()
                .get("certificate/{id}", availableId())
                .then()
                .assertThat()
                .statusCode(200)
                .spec(responseSpec);
    }

    @Test(priority = 4)
    public void updateCertificate() {

        String payload = "{\"name\":\"ISTQB\",\"organization\":\"MyTeam\", \"period\":\"10 LAT\", \"trade\":\"LOGISTYKA\"}";

        given().when()
                .body(payload)
                .put("certificate/{id}", availableId())
                .then()
                .assertThat()
                .statusCode(200)
                .spec(responseSpec)
                .body("name", equalTo("ISTQB"))
                .time(lessThan(4000L));
    }

    @Test(priority = 5)
    public void deleteCertificate() {
        given().when()
                .delete("certificate/{id}", availableId())
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine(containsString("HTTP/1.1 200"))
                .time(lessThan(4000L));
    }

    @Test(priority = 6)
    public void getAllCertificates() {
        given().when()
                .get("certificates/all")
                .then()
                .assertThat()
                .statusCode(200);
    }


}
