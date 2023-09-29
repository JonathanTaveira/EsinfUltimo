package tp1.ui;

import tp1.domain.ChargerData;
import tp1.domain.ChargerDataAnalyzer;
import tp1.domain.FileReader;

import java.util.List;

public class First {
    public static void first(String excelFilePath) {
        // Ler os dados dos arquivos
        List<ChargerData> chargerDataList = FileReader.readChargerData(excelFilePath);

        // Criar uma instância da DataAnalyzer para análise de dados
        ChargerDataAnalyzer chargerDataAnalyzer = new ChargerDataAnalyzer();

        // Analisar os dados dos carregadores elétricos
        chargerDataAnalyzer.analyzeChargerData(chargerDataList);

        // Imprimir dados dos carregadores elétricos por país e cidade em uma tabela compacta
        List<String> chargerDataList1 = chargerDataAnalyzer.getChargerDataListByCountryCity();

        for (String entry : chargerDataList1) {
            System.out.println(entry);
        }
        System.out.println("Returning to the menu...");
    }

}
