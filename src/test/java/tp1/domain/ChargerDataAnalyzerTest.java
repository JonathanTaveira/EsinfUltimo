package tp1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ChargerDataAnalyzerTest {
    private ChargerDataAnalyzer chargerDataAnalyzer;

    @BeforeEach
    public void setUp() {
        chargerDataAnalyzer = new ChargerDataAnalyzer();
    }

    @Test
    public void testAnalyzeChargerData() {
        List<ChargerData> chargerDataList = createSampleChargerDataList();

        chargerDataAnalyzer.analyzeChargerData(chargerDataList);

        List<String> cDA = chargerDataAnalyzer.getChargerDataListByCountryCity();
        int input = cDA.size();

        assertEquals(2, input); // Replace with your expected result
    }


    @Test
    public void testGetChargerDataListByCountryCity() {
        List<String> chargerDataListByCountryCity = chargerDataAnalyzer.getChargerDataListByCountryCity();

        assertNotNull(chargerDataListByCountryCity);
        assertTrue(chargerDataListByCountryCity.size() > 0);
    }

    @Test
    public void testGetChargerDataByCountryAndKw() {
        List<String> chargerDataByCountryAndKw = chargerDataAnalyzer.getChargerDataByCountryAndKw(50);

        assertNotNull(chargerDataByCountryAndKw);
        assertTrue(chargerDataByCountryAndKw.size() > 0);
    }

    @Test
    public void testGetChargerDataByCountryCityAndGPS() {
        List<String> chargerDataByCountryCityAndGPS = chargerDataAnalyzer.getChargerDataByCountryCityAndGPS();

        assertNotNull(chargerDataByCountryCityAndGPS);
        assertTrue(chargerDataByCountryCityAndGPS.size() > 0);
    }

    private List<ChargerData> createSampleChargerDataList() {
        List<ChargerData> chargerDataList = new ArrayList<>();
        chargerDataList.add(new ChargerData(
                "SuperchargerName",
                "123 Main Street",
                "CityName",
                "StateName",
                "12345",
                "CountryName",
                10,
                50,
                "40.7128, -74.0060",
                "100m",
                "Active"
        ));
        chargerDataList.add(new ChargerData(
                "SuperchargerName",
                "123 Main Street",
                "CityName",
                "StateName",
                "12345",
                "CountryName",
                10,
                50,
                "40.7128, -1.0060",
                "100m",
                "Active"
        ));
        return chargerDataList;
    }

    @Test
    public void testGetChargerDataByStalls() {
        List<ChargerData> chargerDataList = createSampleChargerDataList();

        Map<String, Integer> result = chargerDataAnalyzer.getChargerDataByStalls(chargerDataList);

        assertNotNull(result);
        assertEquals(1, result.size()); // Assuming there is only one unique country in the sample data

        // Replace "CountryName" with the actual country name in your sample data
        assertTrue(result.containsKey("CountryName"));
        assertEquals(20, result.get("CountryName")); // Replace 100 with the expected total stalls for the country
    }

}
