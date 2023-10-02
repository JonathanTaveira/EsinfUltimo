package tp1.domain;

import java.util.*;

public class ChargerDataAnalyzer {
    private Map<String, Map<String, Integer>> chargerDataByCountryCity;
    private Map<String, Map<Integer, Integer>> chargerDataByCountryKw;

    public ChargerDataAnalyzer() {
        chargerDataByCountryCity = new HashMap<>();
        chargerDataByCountryKw = new HashMap<>();
    }

    public void analyzeChargerData(List<ChargerData> chargerDataList) {
        for (ChargerData data : chargerDataList) {
            String country = data.getCountry();
            String city = data.getCity();
            int kW = data.getkW();
            int stalls = data.getStalls();

            // Atualizar o mapa por país e cidade
            updateChargerDataByCountryCity(country, city, stalls);

            // Atualizar o mapa por país e kW
            updateChargerDataByCountryKw(country, kW, stalls);
        }
    }

    private void updateChargerDataByCountryCity(String country, String city, int stalls) {
        // Verifica se o país já existe no mapa
        if (!chargerDataByCountryCity.containsKey(country)) {
            chargerDataByCountryCity.put(country, new HashMap<>());
        }

        // Atualiza o mapa interno do país com o número de carregadores (stalls) por cidade
        Map<String, Integer> cityChargerData = chargerDataByCountryCity.get(country);
        cityChargerData.put(city, cityChargerData.getOrDefault(city, 0) + stalls);
    }

    private void updateChargerDataByCountryKw(String country, int kW, int stalls) {
        // Verifica se o país já existe no mapa
        if (!chargerDataByCountryKw.containsKey(country)) {
            chargerDataByCountryKw.put(country, new HashMap<>());
        }

        // Atualiza o mapa interno do país com o número de carregadores (stalls) por kW
        Map<Integer, Integer> kWChargerData = chargerDataByCountryKw.get(country);

        // Verifica se o kW já existe no mapa interno
        if (!kWChargerData.containsKey(kW)) {
            kWChargerData.put(kW, stalls);
        } else {
            // Se o kW já existe, adiciona o número de carregadores (stalls) ao valor existente
            int currentStalls = kWChargerData.get(kW);
            kWChargerData.put(kW, currentStalls + stalls);
        }
    }

    public List<String> getChargerDataListByCountryCity() {
        List<String> result = new ArrayList<>();

        // Cabeçalho da tabela compacta
        result.add(String.format("%-20s %-30s %-10s", "País", "Cidade", "Carregadores"));

        List<String> dataList = new ArrayList<>();

        for (Map.Entry<String, Map<String, Integer>> entry : chargerDataByCountryCity.entrySet()) {
            String country = entry.getKey();
            Map<String, Integer> cityChargerData = entry.getValue();
            for (Map.Entry<String, Integer> cityEntry : cityChargerData.entrySet()) {
                String city = cityEntry.getKey();
                int chargerCount = cityEntry.getValue();

                // Linha da tabela compacta
                String entryString = String.format("%-20s %-30s %-10s", country, city, chargerCount);
                dataList.add(entryString);
            }
        }

        // Ordenar a lista de dados pelo nome do país
        Collections.sort(dataList);

        // Adicionar os dados ordenados à lista de resultados
        result.addAll(dataList);

        return result;
    }

    public List<String> getChargerDataByCountryAndKw(int kWThreshold) {
        List<String> result = new ArrayList<>();

        // Cabeçalho da tabela compacta
        result.add(String.format("%-20s %-10s %-10s %-10s", "País", "kW > " + kWThreshold, "kW <= " + kWThreshold, "Total"));

        List<String> dataList = new ArrayList<>();

        for (Map.Entry<String, Map<Integer, Integer>> entry : chargerDataByCountryKw.entrySet()) {
            String country = entry.getKey();
            Map<Integer, Integer> kWChargerData = entry.getValue();

            int kWAboveThreshold = 0;
            int kWBelowThreshold = 0;
            int totalChargers = 0;

            for (int kW : kWChargerData.keySet()) {
                int stalls = kWChargerData.get(kW);
                if (kW > kWThreshold) {
                    kWAboveThreshold += stalls;
                } else {
                    kWBelowThreshold += stalls;
                }
                totalChargers += stalls;
            }

            // Linha da tabela compacta
            String entryString = String.format("%-20s %-10s %-10s %-10s", country, kWAboveThreshold, kWBelowThreshold, totalChargers);
            dataList.add(entryString);
        }

        // Ordenar a lista de dados pelo número total de carregadores (kW acima do limiar)
        Collections.sort(dataList, (s1, s2) -> {
            int total1 = Integer.parseInt(s1.split("\\s+")[3]);
            int total2 = Integer.parseInt(s2.split("\\s+")[3]);
            if (total1 != total2) {
                return total2 - total1; // Ordem decrescente por total de carregadores
            } else {
                // Em caso de empate, ordem alfabética por país
                return s1.split("\\s+")[0].compareTo(s2.split("\\s+")[0]);
            }
        });

        // Adicionar os dados ordenados à lista de resultados
        result.addAll(dataList);

        return result;
    }


}
