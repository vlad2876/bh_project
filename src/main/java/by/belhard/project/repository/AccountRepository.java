package by.belhard.project.repository;

import by.belhard.project.exceptions.ItemNotFoundException;
import by.belhard.project.model.Account;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class AccountRepository {

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

    public Account getByUsername(String input) throws SQLException {

        PreparedStatement preparedStatement = getConnection().prepareStatement
                ("select * from accounts where username = ?;");
        preparedStatement.setString(1, input);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            throw new ItemNotFoundException("Account");
        } else {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            int cash = resultSet.getInt("cash");

            return new Account(id, username, cash);
        }

    }

    public void updateAccountCash(int newAmount, int id) throws SQLException {

        PreparedStatement preparedStatement = getConnection().prepareStatement
                ("update accounts set cash = ? where id = ?;");
        preparedStatement.setInt(1, newAmount);
        preparedStatement.setInt(2, id);


        preparedStatement.executeUpdate();
    }
}
