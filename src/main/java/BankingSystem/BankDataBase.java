package main.java.BankingSystem;


import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class BankDataBase {


    private final Connection con;

    public BankDataBase(String dbName) throws SQLException {
        createDBFIle(dbName);
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:" + dbName);
        this.con = dataSource.getConnection();
        initDB();
    }

    private void createDBFIle(String dbName) {
        try {
            File myObj = new File(dbName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void initDB() throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS card( " +
                "id INTEGER PRIMARY KEY, " +
                "number TEXT, " +
                "pin TEXT, " +
                "balance INTEGER DEFAULT 0)");
    }

    protected void insertCreditCard(CreditCardAccount card) {
        try (Statement statement = con.createStatement()) {
            String execute = String.format("INSERT INTO card " +
                            "VALUES (%d, %s, %s, %d)"
                    , createId(), card.getCreditCardNumber(), card.getPin(), (int) card.getBalance());
            statement.executeUpdate(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int createId() {
        int newId;
        Random random = new Random();
        do {
            newId = random.nextInt();
        } while (idExists(newId));
        return newId;
    }

    private boolean idExists (int newId) {
        try (Statement statement= con.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id FROM " +
                    "card WHERE id = " + newId);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CreditCardAccount getAccount(String cardNumber) {
        CreditCardAccount account = null;
        try (Statement statement = con.createStatement()) {
            ResultSet accountRecord = statement.executeQuery("SELECT number, pin, " +
                    "balance FROM card WHERE number = " + cardNumber);
            if (accountRecord.next()) {
                account = new CreditCardAccount(accountRecord.getString("number"),
                        accountRecord.getString("pin"),
                        (float) accountRecord.getInt("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public int getBalance(CreditCardAccount account) {
        try (Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT balance FROM card WHERE number = "
                    + account.getCreditCardNumber() + ";")) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addIncome(CreditCardAccount account, int income) {

        try (Statement statement = con.createStatement()) {
            statement.executeUpdate("UPDATE card " +
                    "SET balance = balance + " + income +
                    " WHERE number = " + account.getCreditCardNumber() + ";");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void delete(CreditCardAccount account) {
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate("DELETE FROM card " +
                    " WHERE number = " + account.getCreditCardNumber() + ";");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void doTransfer(CreditCardAccount fromAccountNumber, CreditCardAccount toAccountNumber, int amount) {
        String debitQuery = "UPDATE card SET balance = balance + " + amount + " WHERE number = " +
                toAccountNumber.getCreditCardNumber() + " ;";
        String creditQuery = "UPDATE card SET balance = balance - " + amount + " WHERE number = " +
                fromAccountNumber.getCreditCardNumber() + " ;";
        Savepoint s1 = null;
        try (PreparedStatement p1 = con.prepareStatement(debitQuery);
             PreparedStatement p2 = con.prepareStatement(creditQuery)) {
            con.setAutoCommit(false);
            s1 = con.setSavepoint();
            p1.executeUpdate();
            p2.executeUpdate();
            con.commit();
        } catch (SQLException throwable) {
            try {
                con.rollback(s1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            con.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

