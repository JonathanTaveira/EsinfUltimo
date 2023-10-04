package tp1.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ElectricVehicleDataTest {

    @Test
    public void testGetters() {
        // Create an ElectricVehicleData object with sample data
        ElectricVehicleData evData = new ElectricVehicleData("Country1", "Electric", 2023, 1000);

        // Test the getters for all fields
        assertEquals("Country1", evData.getCountry());
        assertEquals("Electric", evData.getPowertrain());
        assertEquals(2023, evData.getYear());
        assertEquals(1000, evData.getNumberOfVehicles());
    }

    @Test
    public void testEquals() {
        // Create two ElectricVehicleData objects with the same data
        ElectricVehicleData evData1 = new ElectricVehicleData("Country1", "Electric", 2023, 1000);
        ElectricVehicleData evData2 = new ElectricVehicleData("Country1", "Electric", 2023, 1000);

        // Test if the two objects are equal
        assertTrue(evData1.equals(evData2));
    }

    @Test
    public void testNotEquals() {
        // Create two ElectricVehicleData objects with different data
        ElectricVehicleData evData1 = new ElectricVehicleData("Country1", "Electric", 2023, 1000);
        ElectricVehicleData evData2 = new ElectricVehicleData("Country2", "Hybrid", 2022, 500);

        // Test if the two objects are not equal
        assertFalse(evData1.equals(evData2));
    }
}
