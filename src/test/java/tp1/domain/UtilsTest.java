package tp1.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testCalculateDistance() {
        Coordinates point1 = new Coordinates(40.7128, -74.0060); // New York City
        Coordinates point2 = new Coordinates(34.0522, -118.2437); // Los Angeles

        double distance = Utils.calculateDistance(point1, point2);

        // Expected distance between NYC and LA (in Km)
        double expectedDistance = 3935.7462546097217;

        // Allow for a small tolerance due to floating-point precision
        double tolerance = 0.001;

        assertEquals(expectedDistance, distance, tolerance);
    }

    @Test
    public void testParseCoordinates() {
        String coordinatesStr = "40.7128, -74.0060";
        Coordinates coordinates = Utils.parseCoordinates(coordinatesStr);

        // Expected coordinates
        Coordinates expectedCoordinates = new Coordinates(40.7128, -74.0060);

        assertEquals(expectedCoordinates.getLatitude(), coordinates.getLatitude(), 0.001);
        assertEquals(expectedCoordinates.getLongitude(), coordinates.getLongitude(), 0.001);
    }

    @Test
    public void testCalculateMaxDistance() {
        Coordinates point1 = new Coordinates(40.7128, -74.0060); // New York City
        Coordinates point2 = new Coordinates(34.0522, -118.2437); // Los Angeles
        Coordinates point3 = new Coordinates(51.5074, -0.1278); // London

        List<Coordinates> coordinatesList = Arrays.asList(point1, point2, point3);

        double maxDistance = Utils.calculateMaxDistance(coordinatesList);

        // Expected max distance between any two of the three cities
        double expectedDistance = Utils.calculateDistance(point2, point3);

        assertEquals(expectedDistance, maxDistance, 0.001);
    }


    @Test
    public void testCalculateMinDistance() {
        Coordinates point1 = new Coordinates(40.7128, -74.0060); // New York City
        Coordinates point2 = new Coordinates(34.0522, -118.2437); // Los Angeles
        Coordinates point3 = new Coordinates(51.5074, -0.1278); // London

        List<Coordinates> coordinatesList = Arrays.asList(point1, point2, point3);

        double minDistance = Utils.calculateMinDistance(coordinatesList);

        // Expected max distance between any two of the three cities
        double expectedDistance = Utils.calculateDistance(point1, point2);

        assertEquals(expectedDistance, minDistance, 0.001);
    }
}
