package tp1.ui;

import tp1.domain.*;

import java.util.*;

public class Sixth {
    public static void sixth(String excelFilePath) {

        // Ler os dados dos arquivos
        List<ChargerData> chargerDataList = FileReader.readChargerData(excelFilePath);

        // Criar uma instância da DataAnalyzer para análise de dados
        ChargerDataAnalyzer chargerDataAnalyzer = new ChargerDataAnalyzer();

        // Analisar os dados dos carregadores elétricos
        chargerDataAnalyzer.analyzeChargerData(chargerDataList);

        // Obter charger data por país, cidade, e GPS
        List<String> chargerDataByCountryCityAndGPS = chargerDataAnalyzer.getChargerDataByCountryCityAndGPS();
        List<Coordinates> coordinatesList = new ArrayList<>();

        for (String entry : chargerDataByCountryCityAndGPS) {
            String[] parts = entry.split("\\s*\\|\\s*");
            if (parts.length >= 3) {
                // Obter as coordenadas
                String[] gpsParts = parts[2].split(",");
                if (gpsParts.length == 2) {
                    double latitude = Double.parseDouble(gpsParts[0].trim());
                    double longitude = Double.parseDouble(gpsParts[1].trim());

                    // Criar a lista de coordenadas
                    Coordinates coordinates = new Coordinates(latitude, longitude);
                    coordinatesList.add(coordinates);
                }
            }
        }

        // Inserção de Pontos
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of points you want to insert: ");
        int numberOfPoints = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Coordinates> coordinatesListInput = new ArrayList<>();

        for (int i = 1; i <= numberOfPoints; i++) {
            System.out.print("Enter the latitude for P" + i + ": ");
            double latitude = scanner.nextDouble();
            System.out.print("Enter the longitude for P" + i + ": ");
            double longitude = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            Coordinates coordinates = new Coordinates(latitude, longitude);
            coordinatesListInput.add(coordinates);
        }


        // Map para armazenar países, cidades, e carregadores para cada POI
        Map<Coordinates, List<String>> closestCountriesCitiesAndStallsMap = new HashMap<>();

        // Calcular os países e cidades mais próximos para cada POI
        for (int j = 0; j < coordinatesListInput.size(); j++) {
            Coordinates inputCoord = coordinatesListInput.get(j);

            // Lista para armazenar os países, cidades e número de stalls mais próximos para este POI
            List<String> closestCountriesCitiesAndStalls = new ArrayList<>();

            for (int i = 0; i < coordinatesList.size(); i++) {
                Coordinates coord = coordinatesList.get(i);
                double distance = Utils.calculateDistance(inputCoord, coord);

                for (int k = 0; k < coordinatesListInput.size(); k++){
                    double diff = Utils.calculateDistance(coordinatesListInput.get(k), coord);
                    if (diff < distance){
                        distance = 999999999;
                    }
                }


                if (distance != 999999999) {
                    String[] parts = chargerDataByCountryCityAndGPS.get(i).split("\\s*\\|\\s*");
                    if (parts.length >= 4) {
                        String country = parts[0].trim();
                        String city = parts[1].trim();
                        String stalls = parts[3].trim(); // Get the number of stalls
                        closestCountriesCitiesAndStalls.add(country + ", " + city + " (Stalls: " + stalls + ")");
                    }
                }
            }


            closestCountriesCitiesAndStallsMap.put(inputCoord, closestCountriesCitiesAndStalls);
        }

        // Imprimir os países, cidades e número de stalls mais próximos para cada POI, ordenados por stalls
        for (Map.Entry<Coordinates, List<String>> entry : closestCountriesCitiesAndStallsMap.entrySet()) {
            Coordinates poiCoordinates = entry.getKey();
            List<String> closestCountriesCitiesAndStalls = entry.getValue();

            // Ordenar a lista com base no número de stalls
            Collections.sort(closestCountriesCitiesAndStalls, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    // Extrair o número de stalls de cada entrada e comparar
                    int stalls1 = extractStalls(s1);
                    int stalls2 = extractStalls(s2);
                    return Integer.compare(stalls2, stalls1);
                }

                private int extractStalls(String input) {
                    String numericPart = input.replaceAll("[^0-9]", "");
                    if (!numericPart.isEmpty()) {
                        return Integer.parseInt(numericPart);
                    }
                    return 0; // Retorna 0 se a String estiver vazia
                }
            });

            System.out.println("POI at Latitude " + poiCoordinates.getLatitude() + ", Longitude " + poiCoordinates.getLongitude() + ":");
            for (String countryCityStalls : closestCountriesCitiesAndStalls) {
                System.out.println("- " + countryCityStalls);
            }
            System.out.println();
        }
    }
}
