package Helpers.Requests;

import Config.ConfigLoader;
import Model.*;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EmployeeApiHelper {

    public Employee getEmployeeInfo(int employeeId) {
        return given()
                .basePath("employee")
                .when()
                .get("{Id}", employeeId).body().as(Employee.class);
    }

}
