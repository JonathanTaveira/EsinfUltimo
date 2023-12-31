package tp1.ui;

import tp1.domain.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        int numberOfPoints = 0;
        while (true) {
            try {
                numberOfPoints = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        List<Coordinates> coordinatesListInput = new ArrayList<>();

        for (int i = 1; i <= numberOfPoints; i++) {
            double latitude, longitude;

            while (true) {
                System.out.print("Enter the latitude for P" + i + ": ");
                String input = scanner.nextLine();

                try {
                    latitude = Double.parseDouble(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric value for latitude.");
                }
            }

            while (true) {
                System.out.print("Enter the longitude for P" + i + ": ");
                String input = scanner.nextLine();

                try {
                    longitude = Double.parseDouble(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric value for longitude.");
                }
            }

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
                    // Extract the number of stalls from the end of each entry and compare
                    double stalls1 = extractStallsFromEnd(s1);
                    double stalls2 = extractStallsFromEnd(s2);
                    return Double.compare(stalls2, stalls1);
                }

                private double extractStallsFromEnd(String input) {
                    // Use regex to find the last decimal number in the string
                    Matcher matcher = Pattern.compile("\\d+(\\.\\d+)?").matcher(input);
                    double result = 0.0;
                    while (matcher.find()) {
                        // Parse the last match as a double
                        result = Double.parseDouble(matcher.group());
                    }
                    return result;
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
