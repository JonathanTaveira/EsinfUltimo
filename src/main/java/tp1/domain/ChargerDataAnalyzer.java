package tp1.domain;

import java.util.*;

public class ChargerDataAnalyzer {
    private Map<String, Map<String, Integer>> chargerDataByCountryCity;
    private Map<String, Map<Integer, Integer>> chargerDataByCountryKw;
    private Map<String, Double> minimumAutonomyByCountry;
    private Map<String, Map<String, String>> chargerDataByCountryCityLocation;
    private Map<String, Integer> chargerDataByCountryStalls;

    private Map<String, Map<String, Integer>> chargerDataByCountryCityKws;

    private Map<String, Integer> chargerDataStateCapacity;
    private Map<String, Integer> chargerDataStateCapacitySorted;


    public ChargerDataAnalyzer() {
        chargerDataByCountryCity = new HashMap<>();
        chargerDataByCountryKw = new HashMap<>();
        minimumAutonomyByCountry = new HashMap<>();
        chargerDataByCountryCityLocation = new HashMap<>();
        chargerDataByCountryStalls = new HashMap<>();
        chargerDataByCountryCityKws = new HashMap<>();
        chargerDataStateCapacity = new HashMap<>();
        chargerDataStateCapacitySorted = new LinkedHashMap<>();
    }

    public void analyzeChargerData(List<ChargerData> chargerDataList) {
        for (ChargerData data : chargerDataList) {
            String country = data.getCountry();
            String city = data.getCity();
            int kW = data.getkW();
            int stalls = data.getStalls();
            String gps = data.getGps();
            String status = data.getStatus();
            String state = data.getState();

            // Atualizar o mapa por país e cidade
            updateChargerDataByCountryCity(country, city, stalls);

            // Atualizar o mapa por país e kW
            updateChargerDataByCountryKw(country, kW, stalls);

            if (!chargerDataByCountryCityKws.containsKey(country)) {
                chargerDataByCountryCityKws.put(country, new HashMap<>());
            }
            Map<String, Integer> chargerCityCapacity = chargerDataByCountryCityKws.get(country);

            //Calculo CapacidadeCarga
            Integer kws = (data.getkW() * data.getStalls());

            //Guardar para cada Cidade a sua CapacidadeCarga
            chargerCityCapacity.put(city, kws);
            chargerDataByCountryCityKws.replace(country, chargerCityCapacity);

            if (status.equals("Open") && !state.isBlank() && !state.isEmpty()) {
                //Guardar para cada Estado a sua CapacidadeCarga)
                if (!chargerDataStateCapacity.containsKey(state)) {
                    chargerDataStateCapacity.put(state, 0);
                }
                Integer x = chargerDataStateCapacity.get(state) + kws;
                chargerDataStateCapacity.replace(state, x);
            }

            if (!chargerDataByCountryCityLocation.containsKey(country)) {
                chargerDataByCountryCityLocation.put(country, new HashMap<>());
            }

            Map<String, String> chargerMap = chargerDataByCountryCityLocation.get(country);
            chargerMap.put(city, gps);
            chargerDataByCountryCityLocation.replace(country, chargerMap);
        }

        // Converta o mapa em uma lista de entradas (chave-valor)
        List<Map.Entry<String, Integer>> list = new ArrayList<>(chargerDataStateCapacity.entrySet());
        // Ordene a lista com base nos valores em ordem decrescente
        Collections.sort(list, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        for (Map.Entry<String, Integer> entry : list) {
            chargerDataStateCapacitySorted.put(entry.getKey(), entry.getValue());
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
        result.add(String.format("%-20s | %10s | %10s | %10s", "País", "kW > " + kWThreshold, "kW <= " + kWThreshold, "Total"));

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
            String entryString = String.format("%-20s | %10d | %10d | %10d", country, kWAboveThreshold, kWBelowThreshold, totalChargers);
            dataList.add(entryString);
        }

        // Ordenar a lista de dados pelo número total de carregadores
        Collections.sort(dataList, (s1, s2) -> {
            int total1 = Integer.parseInt(s1.split("\\s*\\|\\s*")[3].trim());
            int total2 = Integer.parseInt(s2.split("\\s*\\|\\s*")[3].trim());
            if (total1 != total2) {
                return total2 - total1; // Descending order by total chargers
            } else {
                // In case of a tie, alphabetical order by country
                return s1.split("\\s*\\|\\s*")[0].trim().compareTo(s2.split("\\s*\\|\\s*")[0].trim());
            }
        });

        // Adicionar os dados ordenados à lista de resultados
        result.addAll(dataList);

        return result;
    }


    //André Maia
    public Map<String, Double> calculateMinimumAutonomy() {

        for (Map.Entry<String, Map<String, String>> entry : chargerDataByCountryCityLocation.entrySet()) {

            if (!minimumAutonomyByCountry.containsKey(entry.getKey())) {

                Map<String, String> chargerMap = chargerDataByCountryCityLocation.get(entry.getKey());
                List<Coordinates> coordinatesList = new ArrayList<>();
                // Converter as coordenadas GPS de string para objetos Coordinates
                for (Map.Entry<String, String> cityCharger : chargerMap.entrySet()) {
                    Coordinates coordinates = Utils.parseCoordinates(cityCharger.getValue());
                    coordinatesList.add(coordinates);

                }

                double minimumAutonomy = Utils.calculateMaxDistance(coordinatesList);
                minimumAutonomyByCountry.put(entry.getKey(), minimumAutonomy);
            }
        }
        return minimumAutonomyByCountry;
    }

    public List<String> getChargerDataByCountryCityAndGPS() {
        List<String> result = new ArrayList<>();

        // Cabeçalho da tabela compacta
        result.add(String.format("%-20s | %-30s | %-20s | %-10s", "País", "Cidade", "GPS", "Carregadores"));

        for (Map.Entry<String, Map<String, String>> entry : chargerDataByCountryCityLocation.entrySet()) {
            String country = entry.getKey();
            Map<String, String> cityGPSData = entry.getValue();

            for (Map.Entry<String, String> cityEntry : cityGPSData.entrySet()) {
                String city = cityEntry.getKey();
                String gps = cityEntry.getValue();

                // Obtém o número de carregadores (stalls) para a cidade atual
                int stalls = chargerDataByCountryCity.getOrDefault(country, new HashMap<>()).getOrDefault(city, 0);

                // Linha da tabela compacta
                String entryString = String.format("%-20s | %-30s | %-20s | %-10s", country, city, gps, stalls);
                result.add(entryString);
            }
        }

        return result;
    }

    public Map<String, Integer> getChargerDataByStalls(List<ChargerData> chargerDataList) {
        for (ChargerData chargerData : chargerDataList) {
            String country = chargerData.getCountry();
            if (chargerDataByCountryStalls.containsKey(country)) {
                int newStalls = chargerDataByCountryStalls.get(country) + chargerData.getStalls();
                chargerDataByCountryStalls.put(country, newStalls);
            } else {
                chargerDataByCountryStalls.put(country, chargerData.getStalls());
            }

        }

        return chargerDataByCountryStalls;
    }

    public void getCountryChargersOpenMax ( Integer numero, List<String> dados ) {

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> entry : chargerDataByCountryCityKws.entrySet()) {
            if(dados.contains(entry.getKey())) {
                int maximo = 0;
                result.clear();
                Map<String,Integer> map = chargerDataByCountryCityKws.get(entry.getValue());
                for (Map.Entry<String,Integer> charger : map.entrySet())  {
                       maximo += charger.getValue();
                       result.add(charger.getKey());
                }
                System.out.printf("_____________________________________");
                System.out.printf("Pais :" + entry.getKey() + "\n");
                System.out.printf("Capacidade : " + maximo + "\n");
                System.out.printf("Cidades: ");
                for (String cidade : dados) {
                    System.out.printf(cidade + "\t ");
                }
                System.out.printf("_____________________________________\n");
            }
        }

    }

    public List<String> getStateMax (Integer numero ) {
        List<String> lista = new ArrayList<>();
        System.out.printf("\n __ TOP " + numero + " ESTADOS __ \n");
        int count = 0;
        for (Map.Entry<String, Integer> entry : chargerDataStateCapacitySorted.entrySet()) {
            if (count < numero) {
                lista.add(entry.getKey() + ", ");
                System.out.println(entry.getKey() + ": " + entry.getValue() + " kws");
                count++;
            } else {
                System.out.printf("______ \n\n");
                return lista;
            }
        }
        return null;
    }

}
