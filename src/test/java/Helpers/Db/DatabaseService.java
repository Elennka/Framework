package Helpers.Db;

import Config.ConfigLoader;
import Data.DbSqlRequests;

import java.io.IOException;
import java.sql.*;

public class DatabaseService {
    private Connection connection;

        public void connectToDb() throws SQLException, IOException {

        connection = DriverManager.getConnection(ConfigLoader.getDbUrl(),
                ConfigLoader.getDbUsername(),
                ConfigLoader.getDbPassword());
    }

    public int getAnyCompanyID() throws SQLException {

        String sqlQuery = DbSqlRequests.SQL_GET_ANY_COMPANY_ID;
        ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);
        resultSet.next();
        return resultSet.getInt(1);
    }

    public int getLastCompanyID() throws SQLException {

        String sqlQuery = DbSqlRequests.SQL_GET_LAST_COMPANY_ID;
        ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);
        resultSet.next();
        return resultSet.getInt(1);
    }

    public int getLastEmployeeID() throws SQLException {

        String sqlQuery = DbSqlRequests.SQL_GET_LAST_EMPLOYEE_ID;
        ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);
        resultSet.next();
        return resultSet.getInt(1);
    }

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

    public void deleteCompanyAndItsEmloyees(int companyId) throws SQLException {
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

    public ResultSet getEmployeeInfo(int id) throws SQLException {
        String sqlQuery = DbSqlRequests.SQL_GET_EMPLOYEE_INFO;
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }


    public int getAnyEmployeeId() throws SQLException {
        String sqlQuery = DbSqlRequests.SQL_GET_ANY_EMPLOYEE_ID;
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }
}
