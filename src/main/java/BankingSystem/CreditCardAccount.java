package main.java.BankingSystem;

public class CreditCardAccount {

    private String creditCardNumber;
    private String pin;
    private float balance;

    public CreditCardAccount(String creditCardNumber, String pin, float balance) {
        this.creditCardNumber = creditCardNumber;
        this.pin = pin;
        this.balance = 0.0F;
    }


    //getter and setter
    public float getBalance() {
        return balance;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
