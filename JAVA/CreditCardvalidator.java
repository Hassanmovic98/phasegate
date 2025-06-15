import java.util.Scanner;

public class CreditCardvalidator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
	System.out.print("Hello,welcome to credit card validator section");
        System.out.print("please,prompt in your card numbers:  ");
        String number = input.nextLine().trim();

        if (!number.matches("\\d{13,16}")) {
            System.out.println("Invalid input,must be between 13 and 16 digits.");
            return;
        }

        String cardType = getCardType(number);
        boolean isValid = ifCardIsValid(number);

        if (!cardType.equals("Unknown")) {
            System.out.println("Card Type: " + cardType);
            System.out.println("Card Number is " + (isValid ? "Valid" : "Invalid"));
        } else {
            System.out.println("Sorry,Card not recognized");
        }
    }

    public static String getCardType(String number) {
        if (number.startsWith("4")) return "Visa";
        else if (number.startsWith("5")) return "MasterCard";
        else if (number.startsWith("37")) return "American Express";
        else if (number.startsWith("6")) return "Discover";
        else return "Unknown";
    }

    public static boolean ifCardIsValid(String number) {
        int sumEven = 0;
        int sumOdd = 0;

       
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            
            if ((number.length() - i) % 2 == 1) {
                sumOdd += digit;
            } else {
                int doubled = digit * 2;
                sumEven += (doubled > 9) ? (doubled - 9) : doubled;
            }
        }

        int total = sumEven + sumOdd;
        return total % 10 == 0;
    }
}
