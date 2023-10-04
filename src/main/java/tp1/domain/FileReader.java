package tp1.domain;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.nio.charset.StandardCharsets;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<ChargerData> readChargerData(String csvFilePath) {
        List<ChargerData> dataList = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath), StandardCharsets.ISO_8859_1);
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withSkipLines(1) // Pule a primeira linha (cabeçalho)
                     .withCSVParser(new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(false).build()) // Configurar o delimitador como vírgula
                     .build()) {

            String[] values;

            while ((values = csvReader.readNext()) != null) {
                try {
                    if (values.length == 11) {
                        String supercharger = values[0];
                        String streetAddress = values[1];
                        String city = values[2];
                        String state = values[3];
                        String zip = values[4];
                        String country = values[5];
                        int stalls = Integer.parseInt(values[6]);
                        int kW = Integer.parseInt(values[7]);
                        String gps = values[8];
                        String elevm = values[9];
                        String status = values[10];

                        dataList.add(new ChargerData(supercharger, streetAddress, city, state, zip, country, stalls, kW, gps, elevm, status));
                    }
                } catch (NumberFormatException e) {
                    // Lida com exceção se não for possível fazer a conversão para int
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (com.opencsv.exceptions.CsvValidationException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public static List<ElectricVehicleData> readEVData(String csvFilePath) {
        List<ElectricVehicleData> dataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFilePath)))) {
            // Ignorar a primeira linha
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) { // Ajuste para acomodar os novos campos
                    String country = parts[0].trim();
                    String powertrain = parts[1].trim();
                    String year = parts[2].trim();
                    String numberOfVehicles = parts[3].trim();
                    int years = Integer.parseInt(year);
                    int numVehic = Integer.parseInt(numberOfVehicles);
                    dataList.add(new ElectricVehicleData(country, powertrain, years, numVehic));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
