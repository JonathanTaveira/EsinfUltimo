package tp1.domain;

import java.util.List;

public class Utils {


//    public static double calculateDistance(Coordinates point1, Coordinates point2) {
//        double deltaX = point2.getLongitude() - point1.getLongitude();
//        double deltaY = point2.getLatitude() - point1.getLatitude();
//        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
//    }

    public static double calculateDistance(Coordinates point1, Coordinates point2) {
        final double radius = 6371e3;
        double lat1 = point1.getLatitude()*Math.PI/180;
        double lon1 = point1.getLongitude()*Math.PI/180;
        double lat2 = point2.getLatitude()*Math.PI/180;
        double lon2 = point2.getLongitude()*Math.PI/180;

        //Latitude
        double deltaX = lat2-lat1;
        //Longitude
        double deltaY = lon2-lon1;

        //Fórmula de Haversine
        double a =  Math.sin(deltaX/2) * Math.sin(deltaX/2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaY/2) * Math.sin(deltaY/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = radius * c / 1000; // em Km

        return d;
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

    public static double calculateMinDistance(List<Coordinates> coordinatesList) {
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < coordinatesList.size() - 1; i++) {
            for (int j = i + 1; j < coordinatesList.size(); j++) {

                double distance = calculateDistance(coordinatesList.get(i), coordinatesList.get(j));
                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
        }

        return minDistance;
    }


}
