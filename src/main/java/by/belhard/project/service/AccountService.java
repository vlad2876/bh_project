package by.belhard.project.service;

import by.belhard.project.exceptions.InvalidInputException;
import by.belhard.project.exceptions.ItemNotFoundException;
import by.belhard.project.exceptions.NotEnoughMoneyException;
import by.belhard.project.repository.AccountRepository;
import by.belhard.project.model.Account;

import java.sql.SQLException;


public class AccountService {

    private AccountRepository accountRepository = new AccountRepository();

    public Account getByUsername(String input) throws SQLException {

        Account account = null;

        try {
            account = accountRepository.getByUsername(input);
        } catch (ItemNotFoundException e) {
            System.err.println("I can't found : " + e.getMessage());
        }

        return account;

    }

    public void depositCash(Account account, int amount) throws SQLException {

        if (amount <= 0) {
            throw new InvalidInputException();
        } else {
            account.setCash(account.getCash() + amount);

            accountRepository.updateAccountCash(account.getCash(), account.getId());
        }

    }

    public void cashOut(Account account, int amount) throws SQLException {

        if (amount <= 0) {
            throw new InvalidInputException();
        } else if (amount > account.getCash()) {
            throw new NotEnoughMoneyException();
        } else {
            account.setCash(account.getCash() - amount);

            accountRepository.updateAccountCash(account.getCash(), account.getId());
        }


    }

    public void playTheGame(Account account, int amount) throws SQLException{
        account.setCash(account.getCash() - amount);
        accountRepository.updateAccountCash(account.getCash(), account.getId());

        StringBuilder sb = new StringBuilder();
        String[] symbols = {"☼", "♥", "○", "7", "$", "♂"};

        for (int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * symbols.length);
            String s = symbols[x];
            sb.append(s);
        }
        String str = sb.toString();
        System.out.println(str);

        switch (str) {
            case "☼☼☼":
                account.setCash(account.getCash() + 5);
                accountRepository.updateAccountCash(account.getCash(), account.getId());
                System.out.println("You win 5$");
                break;
            case "♥♥♥":
                account.setCash(account.getCash() + 50);
                accountRepository.updateAccountCash(account.getCash(), account.getId());
                System.out.println("You win 50$");
                break;
            case "○○○":
                account.setCash(account.getCash() + 10);
                accountRepository.updateAccountCash(account.getCash(), account.getId());
                System.out.println("You win 10$");
                break;
            case "777":
                account.setCash(account.getCash() + 1000);
                accountRepository.updateAccountCash(account.getCash(), account.getId());
                System.out.println("You win 1000$");
                break;
            case "$$$":
                account.setCash(account.getCash() + 500);
                accountRepository.updateAccountCash(account.getCash(), account.getId());
                System.out.println("You win 500$");
                break;
            case "♂♂♂":
                account.setCash(account.getCash() + 100);
                accountRepository.updateAccountCash(account.getCash(), account.getId());
                System.out.println("You win 100$");
                break;
            default:
                System.out.println("You lose!");
                break;
        }
    }
}
