package by.belhard.project;

import by.belhard.project.model.Account;

import java.sql.SQLException;

public class AccountService {

    private AccountDao accountDao = new AccountDao();

    public Account checkUsername(String input) throws SQLException {

        return accountDao.checkUsername(input);

    }
}
