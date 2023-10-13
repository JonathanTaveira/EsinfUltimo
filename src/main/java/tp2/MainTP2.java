package tp2;

import java.util.Scanner;

import static tp2.ui.Third.third;
import static tp2.ui.First.first;
import static tp2.ui.Fourth.fourth;
import static tp2.ui.Second.second;

import static tp2.ui.Fifth.fifth;
import static tp2.ui.Sixth.sixth;


public class MainTP2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        // File paths
        String weekDataPath = "VED_180404_week.csv";
        String ICE_HEV_Path = "VED_Static_Data_ICE&HEV.csv";
        String PHEV_EV_Path = "VED_Static_Data_PHEV&EV.csv";

        do {
            displayMenu();
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Option 1 selected");
                    first();
                    break;
                case "2":
                    System.out.println("Option 2 selected");
                    second();
                    break;
                case "3":
                    System.out.println("Option 3 selected");
                    third();
                    break;
                case "4":
                    System.out.println("Option 4 selected");
                    fourth();
                    break;
                case "5":
                    System.out.println("Option 5 selected");
                    fifth();
                    break;
                case "6":
                    System.out.println("Option 6 selected");
                    sixth();
                    break;
                case "7":
                    System.out.println("Exiting the application...");
                    System.exit(0); // Exit the application
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-7).");
                    break;
            }
        } while (choice != "7");

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
