package tp1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ElectricVehicleAnalyzerTest {
    private ElectricVehicleAnalyzer evAnalyzer;

    @BeforeEach
    public void setUp() {
        evAnalyzer = new ElectricVehicleAnalyzer();
    }

    @Test
    public void testAddElectricVehicleData() {
        List<ElectricVehicleData> evDataList = createSampleElectricVehicleDataList();

        int evData = createSampleElectricVehicleDataList().size();

        assertTrue(evData>0);
    }

    @Test
    public void testCalculateGrowthRatesForAllCountries() {
        List<ElectricVehicleData> evDataList = createSampleElectricVehicleDataList();

        for (ElectricVehicleData evData : evDataList) {
            evAnalyzer.addElectricVehicleData(evData);
        }

        Map<String, Double> growthRates = evAnalyzer.calculateGrowthRatesForAllCountries(2010, 2020);

        assertNotNull(growthRates);
        assertFalse(growthRates.isEmpty());
    }


    @Test
    public void testFindCountriesWithNoIncrease() {
        List<ElectricVehicleData> evDataList = createSampleElectricVehicleDataList();

        for (ElectricVehicleData evData : evDataList) {
            evAnalyzer.addElectricVehicleData(evData);
        }

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to find countries with no increase
        evAnalyzer.findCountriesWithNoIncrease();

        // Reset the standard output
        System.setOut(System.out);

        // Ensure that nothing is printed (no countries with no increase)
        assertFalse(outContent.toString().contains("País:"));
    }


    @Test
    public void testCalculateTotalVehicleCountsByYear() {
        List<ElectricVehicleData> evDataList = createSampleElectricVehicleDataList();

        for (ElectricVehicleData evData : evDataList) {
            evAnalyzer.addElectricVehicleData(evData);
        }

        Map<String, Map<Integer, Integer>> totalVehicleCounts = evAnalyzer.calculateTotalVehicleCountsByYear();

        assertNotNull(totalVehicleCounts);
        assertFalse(totalVehicleCounts.isEmpty());
    }


    // Helper method to create sample ElectricVehicleData objects for testing
    private List<ElectricVehicleData> createSampleElectricVehicleDataList() {
        List<ElectricVehicleData> evDataList = new ArrayList<>();
        evDataList.add(new ElectricVehicleData(
                "CountryName1",
                "PowertrainType1",
                2020,
                1000
        ));
        evDataList.add(new ElectricVehicleData(
                "CountryName1",
                "PowertrainType1",
                2010,
                15
        ));
        return evDataList;
    }
}