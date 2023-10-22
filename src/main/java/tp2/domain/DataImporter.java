package tp2.domain;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import tp2.domain.AVLTree;
import tp2.domain.StaticVehicleData;
import tp2.domain.TripData;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataImporter {
    private AVLTree avlTree;

    public DataImporter(AVLTree avlTree) {
        this.avlTree = avlTree;
    }

    public List<String[]> importCSVData(String filename) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filename))
                .withCSVParser(new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build())
                .build()) {
            // Read all rows from the CSV file
            List<String[]> csvData = reader.readAll();
            // Remove the first row (header) if it exists
            if (!csvData.isEmpty()) {
                csvData.remove(0);
            }
            return csvData;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<StaticVehicleData> importStaticVehicleData(String filename) {
        List<StaticVehicleData> staticDataList = new ArrayList<>();
        List<String[]> csvData = importCSVData(filename);

        if (csvData != null) {
            for (String[] data : csvData) {
                StaticVehicleData staticData = new StaticVehicleData(
                        data[0], data[1], data[2], data[3], data[4], data[5], data[6]
                );
                staticDataList.add(staticData);
            }
        }

        return staticDataList;
    }

    public List<TripData> importTripData(String filename) {
        List<TripData> tripDataList = new ArrayList<>();
        List<String[]> csvData = importCSVData(filename);

        if (csvData != null) {
            for (String[] data : csvData) {
                TripData tripData = new TripData(
                        Double.parseDouble(data[0]), data[1], data[2], Long.parseLong(data[3]),
                        Double.parseDouble(data[4]), Double.parseDouble(data[5]),
                        Double.parseDouble(data[6]), Double.parseDouble(data[7]),
                        Integer.parseInt(data[8]), Double.parseDouble(data[9]),
                        Double.parseDouble(data[10]), Double.parseDouble(data[11]),
                        Double.parseDouble(data[12]), Double.parseDouble(data[13]),
                        Double.parseDouble(data[14]), Double.parseDouble(data[15]),
                        Double.parseDouble(data[16]), Double.parseDouble(data[17]),
                        Double.parseDouble(data[18]), Double.parseDouble(data[19]),
                        Double.parseDouble(data[20]), Double.parseDouble(data[21])
                );
                tripDataList.add(tripData);
            }
        }

        return tripDataList;
    }

    public void importAndInsertData(String vehId, List<StaticVehicleData> staticDataList, List<TripData> tripDataList) {
        for (StaticVehicleData staticData : staticDataList) {
            avlTree.insert(vehId, staticData, tripDataList);
        }
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        DataImporter dataImporter = new DataImporter(avlTree);

        // Example of importing and inserting static vehicle data
        List<StaticVehicleData> staticDataICEHEV = dataImporter.importStaticVehicleData("VED_Static_Data_ICE&HEV.csv");
        List<StaticVehicleData> staticDataPHEVEV = dataImporter.importStaticVehicleData("VED_Static_Data_PHEV&EV.csv");

        dataImporter.importAndInsertData("VehId1", staticDataICEHEV, new ArrayList<>());
        dataImporter.importAndInsertData("VehId2", staticDataPHEVEV, new ArrayList<>());

        // Example of importing and inserting trip data
        List<TripData> tripData = dataImporter.importTripData("VED_180404_week.csv");

        for (TripData trip : tripData) {
            dataImporter.importAndInsertData(trip.getVehId(), new ArrayList<>(), List.of(trip));
        }
    }
}
