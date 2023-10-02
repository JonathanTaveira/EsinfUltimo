package tp1.ui;

import tp1.domain.ChargerData;
import tp1.domain.ChargerDataAnalyzer;
import tp1.domain.FileReader;

import java.util.List;

public class Fourth {
    public static void fourth (String excelFilePath) {
        // Ler os dados dos arquivos
        List<ChargerData> chargerDataList = FileReader.readChargerData(excelFilePath);

        // Criar uma instância da DataAnalyzer para análise de dados
        ChargerDataAnalyzer chargerDataAnalyzer = new ChargerDataAnalyzer();

        // Analisar os dados dos carregadores elétricos
        chargerDataAnalyzer.analyzeChargerData(chargerDataList);

        // Obtém a lista de carregadores por país e kW
        List<String> chargerDataByCountryAndKw = chargerDataAnalyzer.getChargerDataByCountryAndKw(150);


        // Imprime a lista na consola
        for (String line : chargerDataByCountryAndKw) {
            System.out.println(line);
        }

        System.out.println("Returning to the menu...");
    }
}
