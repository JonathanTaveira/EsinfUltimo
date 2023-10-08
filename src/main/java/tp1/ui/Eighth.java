package tp1.ui;

import tp1.domain.ChargerData;
import tp1.domain.ChargerDataAnalyzer;
import tp1.domain.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Eighth {

    public static void eighth (String excelFilePath) {

        Scanner scanner = new Scanner(System.in);

        // Ler os dados dos arquivos
        List<ChargerData> chargerDataList = FileReader.readChargerData(excelFilePath);

        // Criar uma instância da DataAnalyzer para análise de dados
        ChargerDataAnalyzer chargerDataAnalyzer = new ChargerDataAnalyzer();

        // Analisar os dados dos carregadores elétricos
        chargerDataAnalyzer.analyzeChargerData(chargerDataList);

        System.out.print("Introduza o top-N estados que quer visualizar: ");
        int n = 0;
        while (true) {
            try {
                n = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Introduza um valor válido !");
            }
        }

        List<String> lista = chargerDataAnalyzer.getStateMax(n);

        System.out.print(" ________ ");

    }


}
