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


        // Criação da tabela de distâncias com cabeçalhos
        int numRows = coordinatesList.size();
        int numCols = coordinatesListInput.size();

        // Lista para armazenar os dados da tabela como strings
        List<String> tableData = new ArrayList<>();

        // Criação dos cabeçalhos da tabela
        StringBuilder headerRow = new StringBuilder("| País | Cidade |");
        for (int i = 1; i <= numCols; i++) {
            headerRow.append(String.format(" POI(%d) |", i));
        }
        tableData.add(headerRow.toString());

        // Preenchimento da tabela e armazenamento dos valores na lista
        for (int i = 0; i < numRows; i++) {
            StringBuilder rowData = new StringBuilder("|");

            // Adicionar o País e a Cidade na primeira e segunda coluna
            String[] parts = chargerDataByCountryCityAndGPS.get(i).split("\\s*\\|\\s*");
            if (parts.length >= 2) {
                String country = parts[0].trim();
                String city = parts[1].trim();
                rowData.append(String.format(" %s | %s |", country, city));
            } else {
                rowData.append(" N/A | N/A |"); // Caso não haja informações de País e Cidade
            }

            for (int j = 0; j < numCols; j++) {
                Coordinates inputCoord = coordinatesListInput.get(j);
                Coordinates coord = coordinatesList.get(i);
                double distance = Utils.calculateDistance(inputCoord, coord);
                rowData.append(String.format(" %.2f |", distance));
            }
            tableData.add(rowData.toString());
        }

        // Imprime a lista de dados da tabela (opcional)
        for (String row : tableData) {
            System.out.println(row);
        }

        // Map para armazenar países, cidades, e carregadores para cada POI
        Map<Coordinates, List<String>> closestCountriesCitiesAndStallsMap = new HashMap<>();

        // Calcular os países e cidades mais próximos para cada POI
        for (int j = 0; j < numCols; j++) {
            Coordinates inputCoord = coordinatesListInput.get(j);

            // Lista para armazenar os países, cidades e número de stalls mais próximos para este POI
            List<String> closestCountriesCitiesAndStalls = new ArrayList<>();

            for (int i = 0; i < numRows; i++) {
                Coordinates coord = coordinatesList.get(i);
                double distance = Utils.calculateDistance(inputCoord, coord);

                // Inserir um scan
                if (distance <= 100) {
                    String[] parts = chargerDataByCountryCityAndGPS.get(i).split("\\s*\\|\\s*");
                    if (parts.length >= 4) {
                        String country = parts[0].trim();
                        String city = parts[1].trim();
                        String stalls = parts[3].trim(); // Get the number of stalls
                        closestCountriesCitiesAndStalls.add(country + ", " + city + " (Stalls: " + stalls + ")");
                    }
                }
            }

            // Ordenar a lista
            closestCountriesCitiesAndStalls.sort((s1, s2) -> {
                int stalls1 = Integer.parseInt(s1.replaceAll(".*\\(Stalls: (\\d+)\\).*", "$1"));
                int stalls2 = Integer.parseInt(s2.replaceAll(".*\\(Stalls: (\\d+)\\).*", "$1"));
                return stalls2 - stalls1;
            });

            closestCountriesCitiesAndStallsMap.put(inputCoord, closestCountriesCitiesAndStalls);
        }

        // Imprimir os países, cidades e número de stalls mais próximos para cada POI
        for (Map.Entry<Coordinates, List<String>> entry : closestCountriesCitiesAndStallsMap.entrySet()) {
            Coordinates poiCoordinates = entry.getKey();
            List<String> closestCountriesCitiesAndStalls = entry.getValue();

            System.out.println("POI at Latitude " + poiCoordinates.getLatitude() + ", Longitude " + poiCoordinates.getLongitude() + ":");
            for (String countryCityStalls : closestCountriesCitiesAndStalls) {
                System.out.println("- " + countryCityStalls);
            }
            System.out.println();
        }


    }
}
