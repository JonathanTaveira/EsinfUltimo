package tp1.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectricVehicleData that)) return false;
        return getYear() == that.getYear() && getNumberOfVehicles() == that.getNumberOfVehicles() && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getPowertrain(), that.getPowertrain());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getPowertrain(), getYear(), getNumberOfVehicles());
    }
}
