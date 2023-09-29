package tp1.domain;

import java.util.*;

public class ChargerDataAnalyzer {
    private Map<String, Map<String, Integer>> chargerDataByCountryCity;


    public ChargerDataAnalyzer() {
        chargerDataByCountryCity = new HashMap<>();
    }

    public void analyzeChargerData(List<ChargerData> chargerDataList) {
        for (ChargerData data : chargerDataList) {
            String country = data.getCountry();
            String city = data.getCity();
            // Verifica se o país já existe no mapa
            if (!chargerDataByCountryCity.containsKey(country)) {
                chargerDataByCountryCity.put(country, new HashMap<>());
            }
            // Atualiza o mapa interno do país com o número de carregadores por cidade
            Map<String, Integer> cityChargerData = chargerDataByCountryCity.get(country);
            cityChargerData.put(city, cityChargerData.getOrDefault(city, 0) + 1);
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








}
