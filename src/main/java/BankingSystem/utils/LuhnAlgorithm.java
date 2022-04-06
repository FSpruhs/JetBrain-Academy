package main.java.BankingSystem.utils;


public class LuhnAlgorithm {

    public static String calculateCheckDigit(String card) {

        String digit;
        int[] digits = new int[card.length()];
        for (int i = 0; i < card.length(); i++) {
            digits[i] = Character.getNumericValue(card.charAt(i));
        }

        for (int i = digits.length - 1; i >= 0; i -= 2) {
            digits[i] += digits[i];

            if (digits[i] >= 10) {
                digits[i] = digits[i] - 9;
            }
        }
        int sum = 0;
        for (int j : digits) {
            sum += j;
        }
        sum = sum * 9;
        digit = sum + "";
        return digit.substring(digit.length() - 1);
    }

    public static boolean checkCreditCard(String card) {
        int nDigits = card.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {

            int d = card.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;


            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }
}
