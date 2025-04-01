import java.util.Scanner;

public class SafeInput {
    /**
     * Gets an integer value within a specified range
     * @param console Scanner to use for input
     * @param prompt Message to display to user
     * @param low Lowest valid value
     * @param high Highest valid value
     * @return The valid integer input
     */
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int input = 0;
        boolean validInput = false;

        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (console.hasNextInt()) {
                input = console.nextInt();
                if (input >= low && input <= high) {
                    validInput = true;
                } else {
                    System.out.println("Input must be between " + low + " and " + high);
                }
            } else {
                System.out.println("You must enter an integer value");
                console.next(); // Clear the invalid input
            }
        } while (!validInput);

        return input;
    }

    /**
     * Gets a Yes or No confirmation from the user
     * @param console Scanner to use for input
     * @param prompt Message to display to user
     * @return true for yes, false for no
     */
    public static boolean getYNConfirm(Scanner console, String prompt) {
        String input;
        boolean validInput = false;
        boolean result = false;

        do {
            System.out.print(prompt + " [Y/N]: ");
            input = console.next().toUpperCase();

            if (input.equals("Y") || input.equals("N")) {
                validInput = true;
                result = input.equals("Y");
            } else {
                System.out.println("You must enter Y or N");
            }
        } while (!validInput);

        return result;
    }
}