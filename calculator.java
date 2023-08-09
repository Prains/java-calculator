import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Приветствие и объяснение пользователю
        System.out.println("Welcome to Calculator App!");
        System.out.println("Please enter an arithmetic expression in the format:");
        System.out.println("<number> <operator> <number>");
        System.out.println("For example: 2 + 3");

        System.out.print("Input: ");
        String input = scanner.nextLine();
        scanner.close();

        try {
            String result = calc(input);
            System.out.println("Output:");
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Output:");
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            throw new Exception("Invalid input format");
        }

        String aStr = tokens[0];
        String operator = tokens[1];
        String bStr = tokens[2];

        boolean isRomanA = isRomanNumeral(aStr);
        boolean isRomanB = isRomanNumeral(bStr);

        if (isRomanA != isRomanB) {
            throw new Exception("Cannot mix Roman and Arabic numerals");
        }

        int a = isRomanA ? convertRomanToInt(aStr) : Integer.parseInt(aStr);
        int b = isRomanB ? convertRomanToInt(bStr) : Integer.parseInt(bStr);

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception("Numbers should be between 1 and 10");
        }

        int result;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new Exception("Invalid operator");
        }

        return isRomanA ? convertIntToRoman(result) : Integer.toString(result);
    }

    private static boolean isRomanNumeral(String str) {
        return str.matches("[IVXLCDM]+");
    }

    private static int convertRomanToInt(String roman) {
        Map<Character, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);
        romanNumerals.put('D', 500);
        romanNumerals.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanNumerals.get(roman.charAt(i));
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }

        return result;
    }

    private static String convertIntToRoman(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Cannot convert zero or negative numbers to Roman numerals");
        }

        String[] romanNumerals = {
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
        };

        if (num <= 10) {
            return romanNumerals[num - 1];
        } else {
            throw new IllegalArgumentException("Number is out of range for Roman numerals conversion");
        }
    }
}
