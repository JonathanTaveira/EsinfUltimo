package tp1.ui;

import tp1.domain.ElectricVehicleAnalyzer;
import tp1.domain.ElectricVehicleData;
import tp1.domain.FileReader;

import java.util.*;


public class Second {
        public static void second(String csvFilePath) {

            // Lê os dados do CSV
            List<ElectricVehicleData> evDataList = FileReader.readEVData(csvFilePath);

            // Cria uma instância do analisador
            ElectricVehicleAnalyzer analyzer = new ElectricVehicleAnalyzer();

            // Adicione os dados lidos ao analisador
            for (ElectricVehicleData evData : evDataList) {
                analyzer.addElectricVehicleData(evData);
            }

            Scanner scanner = new Scanner(System.in);

            int firstYear = getYearInput(scanner, "Enter the first year: ");
            int lastYear;

            while (true) {
                lastYear = getYearInput(scanner, "Enter the last year: ");
                if (lastYear > firstYear) {
                    break;
                } else {
                    System.out.println("Invalid input. The last year must be greater than the first year.");
                }
            }

            if (firstYear < 2010 || firstYear > 2022 || lastYear < 2010 || lastYear > 2022) {
                System.out.println("Invalid input. Years must be between 2010 and 2022.");
                return;
            }


            // Calcula as taxas de crescimento para todos os países
            Map<String, Double> growthRates = analyzer.calculateGrowthRatesForAllCountries(firstYear, lastYear);

            // Crie uma lista ordenada de países
            List<String> sortedCountries = new ArrayList<>(growthRates.keySet());
            Collections.sort(sortedCountries);

            // Imprime a tabela de taxas de crescimento ordenada
            System.out.println("País                 Taxa de Crescimento");
            for (String country : sortedCountries) {
                double growthRate = growthRates.get(country);
                // Formata a taxa de crescimento com duas casas decimais e espaçamento
                String formattedRate = String.format("%.2f", growthRate);
                System.out.printf("%-15s         %s%n", country, formattedRate);
            }


            System.out.println("Returning to main menu... ");
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
