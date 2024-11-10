package Helpers.Db;

import Config.ConfigLoader;
import Data.DbSqlRequests;
import io.qameta.allure.Step;
import java.sql.*;

public class DatabaseService {
    private Connection connection;

    public void connectToDb() throws SQLException {
        connection = DriverManager.getConnection(ConfigLoader.getDbUrl(),
                ConfigLoader.getDbUsername(),
                ConfigLoader.getDbPassword());
    }

    @Step("Получение Id компании")
    public int getAnyCompanyID() throws SQLException {
        String sqlQuery = DbSqlRequests.SQL_GET_ANY_COMPANY_ID;
        ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Step("Создание новой компании")
    public int createNewCompany() throws SQLException {
        String sqlQuery = DbSqlRequests.SQL_ADD_NEW_COMPANY;
        connection.createStatement().executeUpdate(sqlQuery);
        sqlQuery = DbSqlRequests.SQL_GET_SPECIAL_COMPANY_ID;
        ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);
        resultSet.next();
        resultSet.getInt(1);
        return resultSet.getInt(1);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void deleteCompanyAndItsEmloyees() throws SQLException {
        String sqlQuery = DbSqlRequests.SQL_DELETE_EMPLOYEES_OF_SPECIAL_COMPANY;
        connection.createStatement().executeUpdate(sqlQuery);
        sqlQuery = DbSqlRequests.SQL_DELETE_SPECIAL_COMPANY;
        connection.createStatement().executeUpdate(sqlQuery);
    }

    public int createNewEmployee(int companyId) throws SQLException {
        String sqlQuery = DbSqlRequests.SQL_ADD_NEW_EMPLOYEE;
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, companyId);
        statement.executeUpdate();
        sqlQuery = DbSqlRequests.SQL_GET_SPECIAL_EMPLOYEE;
        ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);
        resultSet.next();
        return resultSet.getInt(1);
    }
}
