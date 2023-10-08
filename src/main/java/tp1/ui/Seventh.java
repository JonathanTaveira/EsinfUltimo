package tp1.ui;

import tp1.domain.*;

import java.util.List;
import java.util.Scanner;

public class Seventh {


    public static void seventh(String csvFilePath, String excelFilePath) {


        List<ElectricVehicleData> evDataList = FileReader.readEVData(csvFilePath);

        List<ChargerData> chargerDataList = FileReader.readChargerData(excelFilePath);

        ChargerDataAnalyzer chargerDataAnalyzer = new ChargerDataAnalyzer();

        chargerDataAnalyzer.analyzeChargerData(chargerDataList);

        ElectricVehicleAnalyzer electricVehicleAnalyzer = new ElectricVehicleAnalyzer();


        Scanner scanner = new Scanner(System.in);

        int year = getYearInput(scanner, "Por favor introduza o ano:\n");

        var chargerDataListByStalls = chargerDataAnalyzer.getChargerDataByStalls(chargerDataList);

        electricVehicleAnalyzer.totalNumberEletricVehicleByCountry(evDataList, year);

        List<String> outputConsole = electricVehicleAnalyzer.totalNumberEletricVehiclePerYear(year, chargerDataListByStalls);

        for (String s : outputConsole) {
            System.out.println(s);
        }

    }

    private static int getYearInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            int year = getIntInput(scanner);
            if (year >= 2010 && year <= 2022) {
                return year;
            } else {
                System.out.println("Invalid input. Year must be between 2010 and 2022.");
            }
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}



