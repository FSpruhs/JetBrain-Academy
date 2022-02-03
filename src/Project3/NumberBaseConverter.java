package Project3;


import java.math.BigDecimal;
import java.math.BigInteger;


public class NumberBaseConverter {

    public static String fromDecimalNumberConverter(BigInteger decimalNumber, BigInteger base) {
        StringBuilder solution = new StringBuilder();
        while (!decimalNumber.equals(BigInteger.ZERO) ) {
            BigInteger digit = decimalNumber.mod(base);
            int digitInt = digit.intValue();
            if (digitInt > 9) {
                char c = (char) (65 + digitInt - 10);
                solution.append(c);
            } else {
                solution.append(digitInt);
            }
            decimalNumber = decimalNumber.divide(base);
        }
        return solution.reverse().toString();
    }

    private static int strDigitToInt(char digit) {
        if (digit >= 48 && digit <= 57) {
            return digit - 48;
        } else {
            return digit - 97 + 10;
        }
    }

    public static BigInteger toDecimalNumberConverter(String input, int base) {
        char[] inputChar = input.toCharArray();
        BigInteger solution = BigInteger.ZERO;
        if (testIsZero(input)) {
            return BigInteger.ZERO;
        }
        for (int i = 0; i < inputChar.length ; i++) {
            solution = solution.add(BigInteger.valueOf((long)  Math.pow(base, i) *
                    strDigitToInt(inputChar[inputChar.length - i - 1])));
        }
        return solution;
    }

    private static boolean testIsZero(String input){
        if (input.length() > 1) {
            return false;
        } else if (input.charAt(0) == '0') {
            return true;
        } else {
            return false;
        }
    }

    public static String baseToBase (int baseA, int baseB, String number) {
        BigInteger toDecimal = toDecimalNumberConverter(number, baseA);
        return fromDecimalNumberConverter(toDecimal, BigInteger.valueOf(baseB));
    }

    public static String baseToBaseFractional(int baseA, int baseB, String number){
        String[] numberSplit = number.split("\\.");
        String nonFractionalPart = baseToBase(baseA, baseB, numberSplit[0]);
        String fractionalPart = baseToBaseFractionalPart(baseA, baseB, numberSplit[1]);
        return nonFractionalPart + "." + fractionalPart;
    }

    private static String baseToBaseFractionalPart(int baseA, int baseB, String fractional) {
        BigDecimal decimal = toDecimalFractional(baseA, fractional);
        return fromDecimalFractional(baseB, decimal);
    }

    public static BigDecimal toDecimalFractional(int base, String number) {
        char[] input = number.toCharArray();
        BigDecimal solution = BigDecimal.ZERO;
        for (int i = 0; i < input.length ; i++) {
            solution = solution.add(BigDecimal.valueOf(  Math.pow(base, -i - 1) *
                    strDigitToInt(input[i])));
        }
        return solution;

    }

    public static String fromDecimalFractional(int base, BigDecimal number) {
        StringBuilder solution = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            number = number.multiply(BigDecimal.valueOf(base));
            int digit = number.intValue();
            number = number.subtract(BigDecimal.valueOf(digit));
            if (digit > 9) {
                char c = (char) (65 + digit - 10);
                solution.append(c);
            } else {
                solution.append(digit);
            }
        }
        return solution.toString();
    }
}
