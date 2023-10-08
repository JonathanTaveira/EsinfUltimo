package tp1.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectricVehicleAnalyzer {
    private Map<String, List<ElectricVehicleData>> dataByCountry;
    private Map<String, Map<Integer, Integer>> yearVehicleCounts;

    private Map<String, Integer> totalVehicleByCountry;

    public ElectricVehicleAnalyzer() {
        dataByCountry = new HashMap<>();
        yearVehicleCounts = new HashMap<>();
        totalVehicleByCountry = new HashMap<>();
    }

    public void addElectricVehicleData(ElectricVehicleData evData) {
        String country = evData.getCountry();
        dataByCountry.computeIfAbsent(country, k -> new ArrayList<>()).add(evData);

        int year = evData.getYear();
        String powertrain = evData.getPowertrain();
        int numberOfVehicles = evData.getNumberOfVehicles();

        // Atualiza o número de veículos do ano anterior para cada tipo de powertrain
        Map<Integer, Integer> yearCounts = yearVehicleCounts.computeIfAbsent(country, k -> new HashMap<>());
        yearCounts.put(year, yearCounts.getOrDefault(year, 0) + numberOfVehicles);


        if (!yearVehicleCounts.containsKey(country)) {
            yearVehicleCounts.put(country, new HashMap<>());
        }

        Map<Integer, Integer> lastYearCounts = yearVehicleCounts.get(country);
        lastYearCounts.put(year, numberOfVehicles);
    }


    public Map<String, Double> calculateGrowthRatesForAllCountries(int firstYear, int lastYear) {
        Map<String, Double> growthRates = new HashMap<>();
        Map<String, Map<Integer, Integer>> totalVehicleCounts = calculateTotalVehicleCountsByYear();

        for (String country : totalVehicleCounts.keySet()) {
            Map<Integer, Integer> yearCounts = totalVehicleCounts.get(country);
            int vehiclesInFirstYear = yearCounts.getOrDefault(firstYear, 0);
            int vehiclesInLastYear = yearCounts.getOrDefault(lastYear, 0);

            if (vehiclesInFirstYear == 0) {
                // Para evitar divisão por zero, não calcula a taxa se não houver dados no primeiro ano
                continue;
            }

            double rate = ((double) vehiclesInLastYear - vehiclesInFirstYear) / vehiclesInFirstYear;
            growthRates.put(country, rate);
        }

        return growthRates;
    }

    public void findCountriesWithNoIncrease() {
        for (String country : dataByCountry.keySet()) {
            List<ElectricVehicleData> data = dataByCountry.get(country);
            Map<Integer, Integer> lastYearCounts = yearVehicleCounts.get(country);

            for (int i = 1; i < data.size(); i++) {
                ElectricVehicleData currentYearData = data.get(i);
                int currentYear = currentYearData.getYear();
                int currentYearVehicles = currentYearData.getNumberOfVehicles();
                String currentYearPowertrain = currentYearData.getPowertrain();

                if (lastYearCounts != null && lastYearCounts.containsKey(currentYear - 1)) {
                    int lastYearVehicles = lastYearCounts.get(currentYear - 1);

                    // Verifique se o powertrain é o mesmo
                    ElectricVehicleData lastYearData = data.get(i - 1);
                    String lastYearPowertrain = lastYearData.getPowertrain();

                    if (currentYearVehicles <= lastYearVehicles && currentYearPowertrain.equals(lastYearPowertrain)) {
                        System.out.println("País: " + country);
                        System.out.println("Ano Atual: " + currentYear);
                        System.out.println("Número de Veículos no Ano Atual: " + currentYearVehicles);
                        System.out.println("Ano Anterior: " + (currentYear - 1));
                        System.out.println("Número de Veículos no Ano Anterior: " + lastYearVehicles);
                        System.out.println("Diferença de Veículos: " + (currentYearVehicles - lastYearVehicles));
                        System.out.println("Tipo de Powertrain: " + currentYearPowertrain);
                        System.out.println("-----------------------");
                    }
                }
            }
        }
    }

    public Map<String, Map<Integer, Integer>> calculateTotalVehicleCountsByYear() {
        Map<String, Map<Integer, Integer>> totalVehicleCounts = new HashMap<>();

        // Inicialize a estrutura de dados para cada país e ano
        for (String country : dataByCountry.keySet()) {
            totalVehicleCounts.put(country, new HashMap<>());
        }

        // Itera sobre os países
        for (String country : dataByCountry.keySet()) {
            List<ElectricVehicleData> dataList = dataByCountry.get(country);
            for (ElectricVehicleData evData : dataList) {
                int year = evData.getYear();
                int numberOfVehicles = evData.getNumberOfVehicles();

                // Obtém o mapa para o país e o ano correspondentes
                Map<Integer, Integer> yearCounts = totalVehicleCounts.get(country);

                // Atualiza o número total de veículos para o país e o ano específicos
                int currentTotal = yearCounts.getOrDefault(year, 0);
                yearCounts.put(year, currentTotal + numberOfVehicles);
            }
        }

        return totalVehicleCounts;
    }

    public List<String> totalNumberEletricVehiclePerYear(int year, Map<String, Integer> stallsByCountry) {

        List<String> result = new ArrayList<>();

        result.add(String.format("%-20s | %-30s | %-20s | %-10s", "País", "Stalls", "Veículos Elétricos", "SC Quota"));
        for (var electricVehicleData : stallsByCountry.entrySet()) {
            String country = electricVehicleData.getKey();
            if (!totalVehicleByCountry.containsKey(country)) continue;

            int totalVehicles = totalVehicleByCountry.get(country);

          //  double ratio = (double) stallsByCountry.get(country) / totalVehicles;

            double ratio = 0.1;

            double scQuota = ((stallsByCountry.get(country) * ratio) / totalVehicles) * 100;

            result.add((String.format("%-20s | %-30s | %-20s | %-10.2f%%", country, stallsByCountry.get(country), totalVehicles, scQuota)));

        }
        return result;
    }

    public void totalNumberEletricVehicleByCountry(List<ElectricVehicleData> evDataList, int year) {

        for (ElectricVehicleData electricVehicleData : evDataList) {
            if (electricVehicleData.getYear() == year) {
                String country = electricVehicleData.getCountry();
                if (totalVehicleByCountry.containsKey(country)) {
                    int newTotal = electricVehicleData.getNumberOfVehicles() + totalVehicleByCountry.get(country);
                    totalVehicleByCountry.put(country, newTotal);
                } else {
                    totalVehicleByCountry.put(country, electricVehicleData.getNumberOfVehicles());
                }
            }
        }
    }
}
