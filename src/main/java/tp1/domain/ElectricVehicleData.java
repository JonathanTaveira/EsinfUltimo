package tp1.domain;

public class ElectricVehicleData {
    private String country;
    private String powertrain;
    private int year;
    private int numberOfVehicles;

    public ElectricVehicleData(String country, String powertrain, int year, int numberOfVehicles) {
        this.country = country;
        this.powertrain = powertrain;
        this.year = year;
        this.numberOfVehicles = numberOfVehicles;
    }

    public String getCountry() {
        return country;
    }

    public String getPowertrain() {
        return powertrain;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }
}
