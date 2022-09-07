package fa.training.utils;

import java.util.Scanner;

// for scanner reuse purpose
public class Validator {
    private static Scanner sc = new Scanner(System.in);

    // message: "user input..."
    public static int inputInt(String message) {
        System.out.println(message);
        try {
            return sc.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input");
            return inputInt(message);
        }
    }

    public static String inputString(String message) {
        System.out.println(message);
        try {
            return sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input");
            return inputString(message);
        }
    }
}
