package tp1.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectricVehicleAnalyzer {
    private Map<String, List<ElectricVehicleData>> dataByCountry;
    private Map<String, Map<Integer, Integer>> lastYearVehicleCounts;

    public ElectricVehicleAnalyzer() {
        dataByCountry = new HashMap<>();
        lastYearVehicleCounts = new HashMap<>();
    }
    public void addElectricVehicleData(ElectricVehicleData evData) {
        String country = evData.getCountry();
        dataByCountry.computeIfAbsent(country, k -> new ArrayList<>()).add(evData);

        // Atualiza o número de veículos do ano anterior para cada tipo de powertrain
        int year = evData.getYear();
        String powertrain = evData.getPowertrain();
        int numberOfVehicles = evData.getNumberOfVehicles();

        if (!lastYearVehicleCounts.containsKey(country)) {
            lastYearVehicleCounts.put(country, new HashMap<>());
        }

        Map<Integer, Integer> lastYearCounts = lastYearVehicleCounts.get(country);
        lastYearCounts.put(year, numberOfVehicles);
    }

    public double calculateGrowthRate(String country, int firstYear, int lastYear) {
        List<ElectricVehicleData> data = dataByCountry.get(country);
        if (data == null) {
            return 0.0; // País não encontrado ou sem dados
        }

        int numberOfVehiclesFirstYear = 0;
        int numberOfVehiclesLastYear = 0;

        for (ElectricVehicleData evData : data) {
            if (evData.getYear() == firstYear) {
                numberOfVehiclesFirstYear = evData.getNumberOfVehicles();
            } else if (evData.getYear() == lastYear) {
                numberOfVehiclesLastYear = evData.getNumberOfVehicles();
            }
        }

        if (numberOfVehiclesFirstYear == 0) {
            return 0.0; // Dados do primeiro ano não encontrados
        }

        return (double) (numberOfVehiclesLastYear - numberOfVehiclesFirstYear) / numberOfVehiclesFirstYear;
    }

    public Map<String, Double> calculateGrowthRatesForAllCountries(int firstYear, int lastYear) {
        Map<String, Double> growthRates = new HashMap<>();

        for (String country : dataByCountry.keySet()) {
            double rate = calculateGrowthRate(country, firstYear, lastYear);
            growthRates.put(country, rate);
        }

        return growthRates;
    }

    public void findCountriesWithNoIncrease() {
        for (String country : dataByCountry.keySet()) {
            List<ElectricVehicleData> data = dataByCountry.get(country);
            Map<Integer, Integer> lastYearCounts = lastYearVehicleCounts.get(country);

            for (int i = 1; i < data.size(); i++) {
                ElectricVehicleData currentYearData = data.get(i);
                int currentYear = currentYearData.getYear();
                int currentYearVehicles = currentYearData.getNumberOfVehicles();

                if (lastYearCounts != null && lastYearCounts.containsKey(currentYear - 1)) {
                    int lastYearVehicles = lastYearCounts.get(currentYear - 1);

                    if (currentYearVehicles <= lastYearVehicles) {
                        System.out.println("País: " + country);
                        System.out.println("Ano Atual: " + currentYear);
                        System.out.println("Ano Anterior: " + (currentYear - 1));
                        System.out.println("Diferença de Veículos: " + (currentYearVehicles - lastYearVehicles));

                        // Você pode adicionar a lógica para mostrar o tipo de powertrain aqui
                        System.out.println("Tipo de Powertrain: " + currentYearData.getPowertrain());

                        System.out.println("-----------------------");
                    }
                }
            }
        }
    }


}
