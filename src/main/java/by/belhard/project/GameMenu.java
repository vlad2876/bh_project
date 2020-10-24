package by.belhard.project;

import by.belhard.project.model.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class GameMenu {

    private static final String MAIN_MENU =
            String.format("1. Play - 1$\n2. Balance\n3. Deposit\n4. Cash out\n5. Logout");

    AccountService accountService = new AccountService();

    void start() {

        String input = "";

        while (!"5".equals(input)) {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Enter username:");
                input = reader.readLine();
                Account account = accountService.checkUsername(input);
                if (account != null) {
                    System.out.println("Enter password:");
                    account.checkPassword(input);
                } else {
                    System.err.println("Invalid username!\nTry again");
                    continue;
                }

                input = reader.readLine();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
