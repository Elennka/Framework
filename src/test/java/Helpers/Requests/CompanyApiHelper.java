package Helpers.Requests;

import Config.ConfigLoader;
import Model.AuthRequest;
import Model.AuthResponse;
import Model.CreateCompanyRequest;
import Model.CreateCompanyResponse;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class CompanyApiHelper {
    public AuthResponse auth(String username, String password) {
        AuthRequest authRequest = new AuthRequest(username, password);

        return given()
                .basePath("/auth/login")
                .body(authRequest)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .as(AuthResponse.class);
    }

    public CreateCompanyResponse createCompany() {
        Faker faker=new Faker();
        AuthResponse info = auth(ConfigLoader.getApiUsername(), ConfigLoader.getApiUserPassword());
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest(faker.company().name(), faker.lorem().sentence());

        return given()
                .basePath("company")
                .body(createCompanyRequest)
                .header("x-client-token", info.userToken())
                .contentType(ContentType.JSON)
                .when()
                .post().body().as(CreateCompanyResponse.class);
    }
}
