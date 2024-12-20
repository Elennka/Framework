package Tests.Api;

import Config.ConfigLoader;
import Helpers.Db.DatabaseService;
import Helpers.Requests.CompanyApiHelper;
import Helpers.Requests.EmployeeApiHelper;
import Model.Employee;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeBusinessTests {
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
        employeeId = databaseService.createNewEmployee(companyId);
        databaseService.createNewEmployee(companyId);
        companyHelper = new CompanyApiHelper();
        employeeHelper = new EmployeeApiHelper();

    }


    @AfterAll
    public static void tearDown() throws SQLException {
        databaseService.deleteCompanyAndItsEmloyees();
        databaseService.closeConnection();
    }

    @Test
    @DisplayName("Проверка, что могу получить информацию о сотруднике")
    public void ICanGetEmployeeInfo() throws IOException {
        Employee employee = employeeHelper.getEmployeeInfo(employeeId);
        assertEquals(employee.id(), employeeId);
        assertNotNull(employee.lastName());
        assertNotNull(employee.firstName());
    }

}
