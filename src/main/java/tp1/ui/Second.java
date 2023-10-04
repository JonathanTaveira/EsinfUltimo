package tp1.ui;

import tp1.domain.ElectricVehicleAnalyzer;
import tp1.domain.ElectricVehicleData;
import tp1.domain.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


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

            int firstYear = 2011; // Define o primeiro ano desejado
            int lastYear = 2022;  // Define o último ano desejado

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




}
