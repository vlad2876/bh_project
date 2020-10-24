package by.belhard.project.controller;

import by.belhard.project.exceptions.InvalidInputException;
import by.belhard.project.exceptions.NotEnoughMoneyException;
import by.belhard.project.service.AccountService;
import by.belhard.project.model.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MenuController {

    private static final String MAIN_MENU =
            String.format("♥♥♥♥♥♥♥♥♥♥\nr. rules\n1. Play - 1$\n2. Balance\n3. Deposit\n4. Cash out\n5. Logout\n♥♥♥♥♥♥♥♥♥♥");

    AccountService accountService = new AccountService();

    public void start() {

        String input = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (!"5".equals(input)) {

                try {
                    System.out.println("Enter username:");
                    input = reader.readLine();
                    Account account = accountService.getByUsername(input);
                    if (account != null) {
                        System.out.println("Enter password:");
                        input = reader.readLine();
                        if (account.checkPassword(input)) {
                            startAccountSession(account);
                        } else {
                            System.err.println("Invalid password!\nTry again");
                        }
                    } else {
                        System.err.println("Invalid username!\nTry again");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startAccountSession(Account account) {

        String input = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (!"5".equals(input)) {

                try {
                    System.out.println(MAIN_MENU);
                    input = reader.readLine();

                    switch (input) {
                        case "r":
                            System.out.println("☼☼☼ - 5$\n○○○ - 10$\n♥♥♥ - 50$\n♂♂♂ - 100$\n$$$ - 500$\n777 - JACKPOT!!! (1000$)");
                            break;
                        case "1":
                            if (account.getCash() > 0) {
                                int price = 1;
                                accountService.playTheGame(account, price);
                            } else {
                                throw new NotEnoughMoneyException();
                            }
                            break;
                        case "2":
                            System.out.println("Balance : " + account.getCash());
                            break;
                        case "3":
                            System.out.println("Enter amount of deposit:");
                            int amountToDeposit = Integer.parseInt(reader.readLine());
                            accountService.depositCash(account, amountToDeposit);
                            break;
                        case "4":
                            System.out.println("Enter amount of cash out:");
                            int amountToCashOut = Integer.parseInt(reader.readLine());
                            accountService.cashOut(account, amountToCashOut);
                            break;
                        case "5":
                            System.out.println("See you later!");
                            break;
                        default:
                            throw new InvalidInputException();
                    }
                } catch (NotEnoughMoneyException e) {
                    System.err.println("Not enough money!");
                } catch (InvalidInputException e) {
                    System.err.println("Invalid input!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
