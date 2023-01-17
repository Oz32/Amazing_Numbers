package numbers;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static String instructions = "\nSupported requests:\n" +
            "- enter a natural number to know its properties;\n" +
            "- enter two natural numbers to obtain the properties of the list:\n" +
            "  * the first parameter represents a starting number;\n" +
            "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
            "- two natural numbers and properties to search for;\n" +
            "- a property preceded by minus must not be present in numbers;\n" +
            "- separate the parameters with one space;\n" +
            "- enter 0 to exit.\n";

    static String[] menu = new String[]{"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "EVEN", "ODD", "HAPPY", "SAD"}; // an array of strings for available commands
    static String[] negativeMenu = new String[]{"-BUZZ", "-DUCK", "-PALINDROMIC", "-GAPFUL", "-SPY", "-SQUARE", "-SUNNY", "-JUMPING", "-EVEN", "-ODD", "-HAPPY", "-SAD"}; // an array of strings for available commands
    static String menuNotArray = "[BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD, HAPPY, SAD]"; // same as above, used only for printing of available commands

    public static void main(String[] args) {
        System.out.print("Welcome to Amazing Numbers!\n" + instructions);
        Scanner scanner = new Scanner(System.in);
        while (true) {  // infinite loop for running the program unless user prints 0 for exit
            System.out.print("\nEnter a request: ");
            String inputString = scanner.nextLine();
            if (inputString.equals("") || inputString.equals(" ")) { // this condition checks if the user's input is empty or he entered a space only
                System.out.print(instructions);
                continue;
            } else if (inputString.equals("0")) { // if the user enters 0 the program will stop
                System.out.println("\nGoodbye!");
                break;
            } else if (checkNums(inputString) == 0 && !checkNumIsDigit(inputString)) { // this condition checks if the user's input is a number
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }

            if (checkNums(inputString) > 1) { // this block works with the first parameter, iteration quantity and single or multiple commands
                String[] numbers = inputString.split(" "); // places user's input into array of Strings for further work
                if (!checkNumIsDigit(numbers[0])) {
                    System.out.println("The first parameter should be a natural number.");
                    continue;
                } else if (!checkNumIsDigit(numbers[1])) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                long originalNumber = Long.parseLong(numbers[0]); // this is the original number that we'll be working with
                long iterationQuantity = Long.parseLong(numbers[1]); // iteration quantity
                if (!checkInt(originalNumber)) { // checks if the original number is valid
                    System.out.println("The first parameter should be a natural number.");
                    continue;
                }
                if (!checkInt(iterationQuantity)) { // checks if the iteration quantity is a valid number
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                String[] commands = new String[numbers.length - 2]; // putting user's commands into array of strings excluding original number and iteration quantity, thus only commands are presented
                for (int i = 0; i < commands.length; i++) {
                    commands[i] = numbers[i + 2];
                }

                boolean[] validation = new boolean[commands.length]; // this array of booleans contains values, if the commands, entered by user, are valid. For example, if the user enteres some invalid command like "iven" instead of "even", the array will contain false value, thus this command is invalid
                int falseCount = 0; // counts invalid commands
                for (int i = 0; i < commands.length; i++) {
                    for (int j = 0; j < menu.length; j++) { // this double loop checks if the menu of available commands contains commands entered by user
                        if (menu[j].equalsIgnoreCase(commands[i])) {
                            validation[i] = true;
                            break;
                        } else if (negativeMenu[j].equalsIgnoreCase(commands[i])) {
                            validation[i] = true;
                            break;
                        } else if (j == menu.length - 1) { // if the inner loop ends and user's input is not presented in the menu of available commands, the counter of false commands increases by 1
                            falseCount++;
                        }
                    }
                }

                StringBuilder oneFalse = new StringBuilder(); // a Stringbuilder for a single wrong command
                StringBuilder multipleFalse = new StringBuilder(); // a Stringbuilder for multiple wrong commands
                multipleFalse.append("The properties [");
                if (falseCount == 1) {
                    for (int i = 0; i < validation.length; i++) { // this loop seeks for wrong command and adds it to the Stringbuilder
                        if (!validation[i]) {
                            oneFalse.append("The property [" + commands[i].toUpperCase() + "] is wrong.");
                            break;
                        }
                    }
                    System.out.println(oneFalse); // outputs entire error message
                    System.out.println("Available properties: " + menuNotArray);
                    continue;
                } else if (falseCount > 1) { // this loop seeks for multiple wrong commands and add it to Stringbiulder
                    for (int i = 0; i < validation.length; i++) {
                        if (!validation[i]) {
                            multipleFalse.append(commands[i].toUpperCase() + ", ");
                            break;
                        }
                    }
                    multipleFalse.deleteCharAt(multipleFalse.length() - 1);
                    multipleFalse.deleteCharAt(multipleFalse.length() - 1);// this to deleteCharAt commands exclude extra symbols like comma and space
                    multipleFalse.append("] are wrong.");
                    System.out.println(multipleFalse); // outputs entire error message
                    System.out.println("Available properties: " + menuNotArray);
                    continue;
                }

                if (Arrays.asList(commands).contains("even") && Arrays.asList(commands).contains("odd") // this block of code checks if user's input does not contain mutually exclusive commands like "sunny" and "square"
                        || Arrays.asList(commands).contains("square") && Arrays.asList(commands).contains("sunny")
                        || Arrays.asList(commands).contains("spy") && Arrays.asList(commands).contains("duck")
                        || Arrays.asList(commands).contains("happy") && Arrays.asList(commands).contains("sad")
                        || Arrays.asList(commands).contains("-even") && Arrays.asList(commands).contains("-odd")
                        || Arrays.asList(commands).contains("-square") && Arrays.asList(commands).contains("-sunny")
                        || Arrays.asList(commands).contains("-spy") && Arrays.asList(commands).contains("-duck")
                        || Arrays.asList(commands).contains("-happy") && Arrays.asList(commands).contains("-sad")
                        || Arrays.asList(commands).contains("even") && Arrays.asList(commands).contains("-even")
                        || Arrays.asList(commands).contains("odd") && Arrays.asList(commands).contains("-odd")
                        || Arrays.asList(commands).contains("square") && Arrays.asList(commands).contains("-square")
                        || Arrays.asList(commands).contains("sunny") && Arrays.asList(commands).contains("-sunny")
                        || Arrays.asList(commands).contains("spy") && Arrays.asList(commands).contains("-spy")
                        || Arrays.asList(commands).contains("duck") && Arrays.asList(commands).contains("-duck")
                        || Arrays.asList(commands).contains("happy") && Arrays.asList(commands).contains("-happy")
                        || Arrays.asList(commands).contains("sad") && Arrays.asList(commands).contains("-sad")) {
                    System.out.println("The request contains mutually exclusive properties");
                    System.out.println("There are no numbers with these properties.");
                    System.out.println("Available properties: " + menuNotArray);
                    continue;
                }

                parametrizedOutputMulti(originalNumber, iterationQuantity, commands); // method for checking the numbers

            } else if (checkNums(inputString) == 1) { // this block works with original number and iteration quantity only
                String[] numbers = inputString.split(" "); // places user's input into array of Strings for further work
                if (!checkNumIsDigit(numbers[0])) {
                    System.out.println("The first parameter should be a natural number.");
                    continue;
                } else if (!checkNumIsDigit(numbers[1])) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                long originalNumber = Long.parseLong(numbers[0]); // this is the original number that we'll be working with
                long iterationQuantity = Long.parseLong(numbers[1]); // iteration quantity
                if (!checkInt(originalNumber)) {
                    System.out.println("The first parameter should be a natural number.");
                    continue;
                } else if (!checkInt(iterationQuantity)) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                System.out.println(" "); // these two blocks check if user's input is valid

                for (int i = 0; i < iterationQuantity; i++) { // this for loop outputs properties of required numbers, starting from the original number and up to the end of iteration quantity
                    outPut(originalNumber);
                    originalNumber++;
                }
            } else if (checkNums(inputString) == 0) { // this block outputs properties of the entered number
                long scanned = Long.parseLong(inputString);
                if (checkInt(scanned)) {
                    String formattedNumber = String.format(Locale.US, "%,d", scanned);
                    System.out.println("\nProperties of " + formattedNumber);
                    System.out.println("       buzz: " + buzz(scanned) + "\n" +
                            "       duck: " + duck(scanned) + "\n" +
                            "palindromic: " + reverse(scanned) + "\n" +
                            "     gapful: " + gapful(scanned) + "\n" +
                            "        spy: " + spy(scanned) + "\n" +
                            "     square: " + square(scanned) + "\n" +
                            "      sunny: " + sunny(scanned) + "\n" +
                            "    jumping: " + jumping(scanned) + "\n" +
                            "       even: " + even(scanned) + "\n" +
                            "        odd: " + odd(scanned) + "\n" +
                            "      happy: " + happyOrSad(scanned) + "\n" +
                            "        sad: " + (happyOrSad(scanned).equals("true") ? "false" : "true"));
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
        }
    }

    // below are methods for the main method
    public static boolean checkInt(long scanned) { // checks if the entered number is positive
        boolean proceed;
        if (scanned > 0) {
            proceed = true;
        } else {
            proceed = false;
        }
        return proceed;
    }

    public static String duck(long scanned) { // this method checks if the number is duck
        String result = "";
        boolean flag = false;
        String s = Long.toString(scanned);
        char[] string = s.toCharArray();
        for (int i = 1; i < string.length; i++) {
            if (string[i] == '0') {
                flag = true;
                result = "true";
                break;
            }
        }
        if (!flag) {
            result = "false";
        }
        return result;
    }

    public static String reverse(long scanned) { // this method checks if the number is palindromic
        String result = "";
        StringBuilder original = new StringBuilder();
        original.append(Long.toString(scanned));
        StringBuilder reversed = new StringBuilder();
        reversed.append(original);
        reversed.reverse();
        if (original.toString().equals(reversed.toString())) {
            result = "true";
        } else {
            result = "false";
        }
        return result;
    }

    public static int checkNums(String inputString) { // this method checks how many numbers and commands the user have entered
        int indicator = 0;
        char[] array = inputString.toCharArray();
        for (char c : array) {
            if (c == ' ') {
                indicator++;
            }
        }
        return indicator;
    }

    public static String gapful(long scanned) { // this method checks if the number is gapful
        String inputString = Long.toString(scanned);
        String result = "";
        long tempLong = Long.parseLong(inputString);
        char[] array = inputString.toCharArray();
        long[] gaps = new long[2];
        gaps[0] = Character.getNumericValue(array[0]);
        gaps[1] = Character.getNumericValue(array[array.length - 1]);
        long gapNumber = Long.parseLong(gaps[0] + "" + gaps[1]);
        if (inputString.length() < 3) {
            result = "false";
        } else if (tempLong % gapNumber == 0) {
            result = "true";
        } else {
            result = "false";
        }
        return result;
    }

    public static String even(long scanned) { // this method checks if the number is even
        return scanned % 2 == 0 ? "true" : "false";
    }

    public static String odd(long scanned) { // this method checks if the number is odd
        return scanned % 2 != 0 ? "true" : "false";
    }

    public static String buzz(long scanned) { // this method checks if the number is buzz
        String result = "";
        boolean flag;
        long lastDigit = scanned % 10;
        long remainingNumber = scanned / 10;
        long buzz = remainingNumber - lastDigit * 2;
        flag = buzz % 7 == 0;
        if (flag || lastDigit == 7) {
            result = "true";
        } else {
            result = "false";
        }
        return result;
    }

    public static String spy(long scanned) { // this method checks if the number is spy
        long scanned1 = scanned;
        long numbersCount = 0;
        do {
            numbersCount++;
            scanned1 /= 10;
        } while (scanned1 != 0);
        long sum = 0;
        long product = 1;
        String result = "";
        for (int i = 0; i < numbersCount; i++) {
            sum = sum + (scanned % 10);
            product = product * (scanned % 10);
            scanned /= 10;
        }
        if (sum == product) {
            result = "true";
        } else result = "false";
        return result;
    }

    public static boolean checkNumIsDigit(String scanned) throws NumberFormatException { // this method checks if the number is actual number, otherwise throws exception
        try {
            Long.parseLong(scanned);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String sunny(long scannned) { // this method checks if the number is sunny
        double a = (double) scannned + 1;
        double b = Math.sqrt(a) % 1;
        return b == 0.0 ? "true" : "false";
    }

    public static String square(long scannned) { // this method checks if the number is square
        double a = (double) scannned;
        double b = Math.sqrt(a) % 1;
        return b == 0.0 ? "true" : "false";
    }

    public static String jumping(long scanned) { // this method checks if the number is jumping
        String s = Long.toString(scanned);
        String isJumping = "true";
        String[] arrs = s.split("");
        int[] arri = new int[arrs.length];
        for (int i = 0; i < arri.length; i++) {
            arri[i] = Integer.parseInt(arrs[i]);
        }
        for (int a = 1; a < arri.length; a++) {
            if (arri[a] - arri[a - 1] != 1 && arri[a] - arri[a - 1] != -1) {
                isJumping = "false";
                break;
            }
        }
        if (scanned >= 0 && scanned <= 9) {
            isJumping = "true";
        }
        return isJumping;
    }

    public static String happyOrSad(long scanned) { // this method checks if the number is happy or sad
        if (scanned == 1 || scanned == 7)
            return "true";
        long sum = scanned, x = scanned;
        while (sum > 9) {
            sum = 0;
            while (x > 0) {
                long d = x % 10;
                sum += d * d;
                x /= 10;
            }
            if (sum == 1)
                return "true";
            x = sum;
        }
        if (sum == 7)
            return "true";
        return "false";
    }


    public static void outPut(long originalNumber) { // outputs the properties according to the given format
        System.out.print("            " + originalNumber + " is ");
        String buzz = buzz(originalNumber).equals("true") ? "buzz, " : "";
        String duck = duck(originalNumber).equals("true") ? "duck, " : "";
        String gapful = gapful(originalNumber).equals("true") ? "gapful, " : "";
        String spy = spy(originalNumber).equals("true") ? "spy, " : "";
        String palindromic = reverse(originalNumber).equals("true") ? "palindromic, " : "";
        String square = square(originalNumber).equals("true") ? "square, " : "";
        String sunny = sunny(originalNumber).equals("true") ? "sunny, " : "";
        String jumping = jumping(originalNumber).equals("true") ? "jumping, " : "";
        String even = even(originalNumber).equals("true") ? "even, " : "";
        String odd = odd(originalNumber).equals("true") ? "odd, " : "";
        String happy = happyOrSad(originalNumber).equals("true") ? "happy, " : "";
        String sad = happyOrSad(originalNumber).equals("false") ? "sad, " : "";
        String stringFormatted = String.format("%s%s%s%s%s%s%s%s%s%s%s%s", buzz, duck, palindromic, gapful, spy, square, sunny, jumping, even, odd, happy, sad);
        stringFormatted = stringFormatted.substring(0, stringFormatted.length() - 1);
        stringFormatted = stringFormatted.substring(0, stringFormatted.length() - 1); // exludes extra symbols like comma and space
        System.out.println(stringFormatted);// outputs formatted string
    }

    public static void parametrizedOutputMulti(long originalNumber, long iterationQuantity, String[] commands) { // this method outputs properties of the required numbers, according to user's commands
        boolean[] proceed = new boolean[commands.length]; // array of booleans, containing values if the number meets the commands entered by user
        long counter = 0; // this counter increases by 1 if the number is in line with user's commands
        while (counter != iterationQuantity) { // this loop iterates over all numbers, starting from the original one and up to the iteration quantity, that meet all commands entered by user
            for (int i = 0; i < commands.length; i++) { //this loop resets array of booleans for each new iteration
                proceed[i] = false;
            }
            for (int i = 0; i < commands.length; i++) { // below conditions check if the user's command equals to the element of available commands menu and checks if the number has the required property
                if (commands[i].equalsIgnoreCase("buzz") && buzz(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("duck") && duck(originalNumber).equals("true")
                        || commands[i].equalsIgnoreCase("-spy") && duck(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("palindromic") && reverse(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("gapful") && gapful(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("spy") && spy(originalNumber).equals("true")
                        || commands[i].equalsIgnoreCase("-duck") && spy(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("square") && square(originalNumber).equals("true")
                        || commands[i].equalsIgnoreCase("-sunny") && square(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("sunny") && sunny(originalNumber).equals("true")
                        || commands[i].equalsIgnoreCase("-square") && sunny(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("jumping") && jumping(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("even") && even(originalNumber).equals("true")
                        || commands[i].equalsIgnoreCase("-odd") && even(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("odd") && odd(originalNumber).equals("true")
                        || commands[i].equalsIgnoreCase("-even") && odd(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("happy") && happyOrSad(originalNumber).equals("true")
                        || commands[i].equalsIgnoreCase("-sad") && happyOrSad(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("sad") && happyOrSad(originalNumber).equals("false")
                        || commands[i].equalsIgnoreCase("-happy") && happyOrSad(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-buzz") && buzz(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-duck") && duck(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-palindromic") && reverse(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-gapful") && gapful(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-spy") && spy(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-square") && square(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-sunny") && sunny(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-jumping") && jumping(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-even") && even(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-odd") && odd(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-happy") && happyOrSad(originalNumber).equals("false")) {
                    proceed[i] = true;
                    continue;
                } else if (commands[i].equalsIgnoreCase("-sad") && happyOrSad(originalNumber).equals("true")) {
                    proceed[i] = true;
                    continue;
                }
            }
            boolean positive = true; // this boolean is used, if the number meets all required properties

            for (boolean b : proceed) { // this foreach loop checks if the number doesn't meet even a single property, it becomes false
                if (!b) {
                    positive = false;
                    break;
                }
            }
            if (positive) { // this condition checks if the boolean above is true, which means that the number is in line with all required properties and it can be printed
                outPut(originalNumber);
                counter++; // increases the value, which is responsible for correct numbers, up to iterationQuantity number
            }
            originalNumber++; // increases original number by 1
        }
    }
}