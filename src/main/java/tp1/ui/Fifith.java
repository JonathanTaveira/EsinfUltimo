package tp1.ui;

import tp1.domain.ChargerData;
import tp1.domain.ChargerDataAnalyzer;
import tp1.domain.FileReader;

import java.util.List;
import java.util.Map;

public class Fifith {

    public static void fifith (String excelFilePath) {

        // Ler os dados dos arquivos
        List<ChargerData> chargerDataList = FileReader.readChargerData(excelFilePath);

        // Criar uma instância da DataAnalyzer para análise de dados
        ChargerDataAnalyzer chargerDataAnalyzer = new ChargerDataAnalyzer();

        // Analisar os dados dos carregadores elétricos
        chargerDataAnalyzer.analyzeChargerData(chargerDataList);

        Map<String, Double> calculateMinimumAutonomy = chargerDataAnalyzer.calculateMinimumAutonomy();
        if (!calculateMinimumAutonomy.isEmpty()) {
            System.out.println("País                    Autonomia Mínima");

            for (Map.Entry<String, Double> entry : calculateMinimumAutonomy.entrySet()) {
                String country = entry.getKey();
                double minimumAutonomy = entry.getValue();
                System.out.printf("%-15s         %.2f%n", country, minimumAutonomy);
            }
        }



        System.out.println("\n Returning to the menu...");
    }
}
