package tp1;

import java.util.Scanner;

import static tp1.ui.First.first;
import static tp1.ui.Fourth.fourth;
import static tp1.ui.Second.second;
import static tp1.ui.Third.third;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        // File paths
        String evFilePath = "ev_sales.csv";
        String chargerFilePath = "carregadores_europa.csv";

        do {
            displayMenu();
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Option 1 selected");
                    first(chargerFilePath);
                    break;
                case "2":
                    System.out.println("Option 2 selected");
                    second(evFilePath);
                    break;
                case "3":
                    System.out.println("Option 3 selected");
                    third(evFilePath);
                    break;
                case "4":
                    System.out.println("Option 4 selected");
                    fourth(chargerFilePath);
                    break;
                case "5":
                    System.out.println("Option 5 selected");
                    break;
                case "6":
                    System.out.println("Option 6 selected");
                    break;
                case "7":
                    System.out.println("Option 7 selected");
                    break;
                case "8":
                    System.out.println("Option 8 selected");
                    break;
                case "9":
                    System.out.println("Exiting the application...");
                    System.exit(0); // Exit the application
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-9).");
                    break;
            }
        } while (choice != "9");

        scanner.close();
    }


    public static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Option 1");
        System.out.println("2. Option 2");
        System.out.println("3. Option 3");
        System.out.println("4. Option 4");
        System.out.println("5. Option 5");
        System.out.println("6. Option 6");
        System.out.println("7. Option 7");
        System.out.println("8. Option 8");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

}
