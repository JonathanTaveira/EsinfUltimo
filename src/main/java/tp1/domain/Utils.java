package tp1.domain;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static double calculateDistance(Coordinates point1, Coordinates point2) {
        double deltaX = point2.getLongitude() - point1.getLongitude();
        double deltaY = point2.getLatitude() - point1.getLatitude();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    public static Coordinates parseCoordinates(String coordinatesStr) {
        String[] parts = coordinatesStr.split(", ");
        double latitude = Double.parseDouble(parts[0]);
        double longitude = Double.parseDouble(parts[1]);
        return new Coordinates(latitude, longitude);
    }

    public static double calculateMaxDistance(List<Coordinates> coordinatesList) {
        double maxDistance = 0.0;

        for (int i = 0; i < coordinatesList.size() - 1; i++) {
            for (int j = i + 1; j < coordinatesList.size(); j++) {

                double distance = calculateDistance(coordinatesList.get(i), coordinatesList.get(j));
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
            }
        }

        return maxDistance;
    }

}
