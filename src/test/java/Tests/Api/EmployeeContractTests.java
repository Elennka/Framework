package Tests.Api;

import Config.ConfigLoader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Helpers.Db.DatabaseService;
import Helpers.Requests.CompanyApiHelper;
import Helpers.Requests.EmployeeApiHelper;
import java.io.IOException;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class EmployeeContractTests {

    static EmployeeApiHelper employeeHelper;
    static CompanyApiHelper companyHelper;
    static DatabaseService databaseService;
    static int companyId;
    static int employeeId;

    @BeforeAll
    public static void setUp() throws SQLException, IOException {
        RestAssured.baseURI = ConfigLoader.getApiUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        databaseService = new DatabaseService();
        databaseService.connectToDb();
        companyId = databaseService.createNewCompany();
        employeeId=databaseService.createNewEmployee(companyId);
        companyHelper = new CompanyApiHelper();
        employeeHelper = new EmployeeApiHelper();
    }


    @AfterAll
    public static void tearDown() throws SQLException {
        databaseService.deleteCompanyAndItsEmloyees();
        databaseService.closeConnection();
    }

    @Test
    @DisplayName("Получает список работников по существующему id компании")
    public void status200OnGetEmployeesByCompany() throws SQLException {
        int id = databaseService.getAnyCompanyID();
        given()
                .basePath("employee")
                .queryParam("company", id)
                .when()
                .get()
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8");
    }
}
