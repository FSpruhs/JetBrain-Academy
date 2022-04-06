package main.java.BankingSystem;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            BankDataBase db = new BankDataBase(args[1]);
            BankMenu.db = db;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        BankMenu.startMenu();

    }
}

