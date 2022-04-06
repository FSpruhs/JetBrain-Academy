package main.java.BankingSystem.utils;

import java.util.Random;

public class RandomUtils {
    private static Random random = new Random();

    public static String createCreditCardNumber(Integer lengthNumber) {
        StringBuilder creditCardNumber = new StringBuilder("400000");
        for (int i = 0; i < lengthNumber - 6; i++) {
            creditCardNumber.append(random.nextInt(10));
        }
        return creditCardNumber.toString();
    }

    public static String createPin() {
        int lowerBound = 1000;
        int upperBound = 9999;
        int pin = random.nextInt(upperBound - lowerBound + 1)  + lowerBound;
        return Integer.toString(pin);
    }
}
