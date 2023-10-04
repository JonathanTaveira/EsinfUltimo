package tp1.ui;

import tp1.domain.ElectricVehicleAnalyzer;
import tp1.domain.ElectricVehicleData;
import tp1.domain.FileReader;

import java.util.List;


public class Third {

    public static void third(String csvFilePath) {

        // Lê os dados do CSV
        List<ElectricVehicleData> evDataList = FileReader.readEVData(csvFilePath);

        // Cria uma instância do analisador
        ElectricVehicleAnalyzer analyzer = new ElectricVehicleAnalyzer();

        // Adicione os dados lidos ao analisador
        for (ElectricVehicleData evData : evDataList) {
            analyzer.addElectricVehicleData(evData);
        }

        analyzer.findCountriesWithNoIncrease();

    }
}
