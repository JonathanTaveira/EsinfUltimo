package tp1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Test
    public void testTotalNumberElectricVehiclePerYear() {
        ElectricVehicleAnalyzer evAnalyzer = new ElectricVehicleAnalyzer();
        List<ElectricVehicleData> evDataList = createSampleElectricVehicleDataList();

        Map<String, Integer> stallsByCountry = new HashMap<>();
        stallsByCountry.put("CountryName1", 50); // Add some stalls for CountryName1

        evAnalyzer.totalNumberEletricVehicleByCountry(evDataList, 2020); // Calculate total vehicles by country
        List<String> result = evAnalyzer.totalNumberEletricVehiclePerYear(2020, stallsByCountry);

        // Ensure that the result contains the expected output
        assertEquals(2, result.size());
        assertTrue(result.get(0).contains("País"));
        assertTrue(result.get(1).contains("CountryName1"));
    }


    @Test
    public void testTotalNumberElectricVehicleByCountry() throws NoSuchFieldException, IllegalAccessException {
        ElectricVehicleAnalyzer evAnalyzer = new ElectricVehicleAnalyzer();
        List<ElectricVehicleData> evDataList = createSampleElectricVehicleDataList();

        // Use reflection to access the private field totalVehicleByCountry
        Field field = ElectricVehicleAnalyzer.class.getDeclaredField("totalVehicleByCountry");
        field.setAccessible(true);

        // Create and initialize the map
        Map<String, Integer> totalVehicleByCountry = new HashMap<>();
        totalVehicleByCountry.put("CountryName1", 0); // Initialize with zero value

        // Set the initialized map to the private field in the ElectricVehicleAnalyzer instance
        field.set(evAnalyzer, totalVehicleByCountry);

        // Perform the operation you want to test
        evAnalyzer.totalNumberEletricVehicleByCountry(evDataList, 2020);

        // Check if the total vehicles for "CountryName1" in 2020 is as expected
        assertEquals(1000, totalVehicleByCountry.get("CountryName1"));
    }
}