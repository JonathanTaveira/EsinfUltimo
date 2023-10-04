package tp1.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChargerDataTest {

    @Test
    public void testGetters() {
        // Create a ChargerData object with sample data
        ChargerData chargerData = new ChargerData(
                "Supercharger1",
                "123 Main St",
                "City1",
                "State1",
                "12345",
                "Country1",
                5,
                250,
                "GPS Coordinates",
                "Elevation",
                "Active"
        );

        // Test the getters for all fields
        assertEquals("Supercharger1", chargerData.getSupercharger());
        assertEquals("123 Main St", chargerData.getStreetAddress());
        assertEquals("City1", chargerData.getCity());
        assertEquals("State1", chargerData.getState());
        assertEquals("12345", chargerData.getZip());
        assertEquals("Country1", chargerData.getCountry());
        assertEquals(5, chargerData.getStalls());
        assertEquals(250, chargerData.getkW());
        assertEquals("GPS Coordinates", chargerData.getGps());
        assertEquals("Elevation", chargerData.getElevm());
        assertEquals("Active", chargerData.getStatus());
    }

    @Test
    public void testEquals() {
        // Create two ChargerData objects with the same data
        ChargerData chargerData1 = new ChargerData("Supercharger1", "Street1", "City1", "State1", "12345", "Country1", 5, 250, "GPS1", "Elevm1", "Active");
        ChargerData chargerData2 = new ChargerData("Supercharger1", "Street1", "City1", "State1", "12345", "Country1", 5, 250, "GPS1", "Elevm1", "Active");

        // Test if the two objects are equal
        assertTrue(chargerData1.equals(chargerData2));
    }

    @Test
    public void testNotEquals() {
        // Create two ChargerData objects with different data
        ChargerData chargerData1 = new ChargerData("Supercharger1", "Street1", "City1", "State1", "12345", "Country1", 5, 250, "GPS1", "Elevm1", "Active");
        ChargerData chargerData2 = new ChargerData("Supercharger2", "Street2", "City2", "State2", "67890", "Country2", 3, 300, "GPS2", "Elevm2", "Inactive");

        // Test if the two objects are not equal
        assertFalse(chargerData1.equals(chargerData2));
    }
}
