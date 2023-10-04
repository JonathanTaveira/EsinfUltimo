package tp1.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class FileReaderTest {
    private String chargerFilePath;
    private String evFilePath;

    @Before
    public void setUp() {
        // Set up paths to your temporary test CSV files
        chargerFilePath = "carregadores_europa.csv";
        evFilePath = "ev_sales.csv";
    }

    @After
    public void tearDown() {
        // Clean up temporary files or resources if necessary
    }

    @Test
    public void testReadChargerData() {
        List<ChargerData> chargerDataList = FileReader.readChargerData(chargerFilePath);

        // Assert that the chargerDataList is not null
        assertNotNull(chargerDataList);

        // Perform more assertions as needed based on your test data
        // For example, you can check the size of the list, specific data in the list, etc.
        assertEquals(1109, chargerDataList.size());
        assertEquals("Bj\u009Arkliden, Sweden", chargerDataList.get(0).getSupercharger());
        assertEquals("Bj\u009Arkliden", chargerDataList.get(0).getCity());
        // Add more assertions based on your test data
    }

    @Test
    public void testReadEVData() {
        List<ElectricVehicleData> evDataList = FileReader.readEVData(evFilePath);

        // Assert that the evDataList is not null
        assertNotNull(evDataList);

        // Perform more assertions as needed based on your test data
        // For example, you can check the size of the list, specific data in the list, etc.
        assertEquals(834, evDataList.size());
        assertEquals("Australia", evDataList.get(0).getCountry());
        assertEquals("BEV", evDataList.get(0).getPowertrain());
        // Add more assertions based on your test data
    }
}
