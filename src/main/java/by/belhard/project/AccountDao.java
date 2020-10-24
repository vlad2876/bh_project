package by.belhard.project;

import by.belhard.project.model.Account;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class AccountDao {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bh_project?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Connection getConnection() throws SQLException {

        if (connection == null) {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            this.connection = connection;
        }

        return connection;
    }

    public Account checkUsername(String input) throws SQLException {

        PreparedStatement preparedStatement = getConnection().prepareStatement
                ("select username from accounts where username = ?;");
        preparedStatement.setString(1, input);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();

    }
}
