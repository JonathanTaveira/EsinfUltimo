package tp2.domain;

public class StaticVehicleData {
    private String vehicleType;
    private String vehicleClass;
    private String engineConfigAndDisplacement;
    private String transmission;
    private String driveWheels;
    private double generalizedWeight;
    private String vehId;

    public StaticVehicleData(String vehId, String vehicleType, String vehicleClass, String engineConfigAndDisplacement,
                             String transmission, String driveWheels, double generalizedWeight) {
        this.vehId = vehId;
        this.vehicleType = vehicleType;
        this.vehicleClass = vehicleClass;
        this.engineConfigAndDisplacement = engineConfigAndDisplacement;
        this.transmission = transmission;
        this.driveWheels = driveWheels;
        this.generalizedWeight = generalizedWeight;
    }

    // Métodos getters
    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public String getEngineConfigAndDisplacement() {
        return engineConfigAndDisplacement;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getDriveWheels() {
        return driveWheels;
    }

    public double getGeneralizedWeight() {
        return generalizedWeight;
    }

    // Métodos setters
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public void setEngineConfigAndDisplacement(String engineConfigAndDisplacement) {
        this.engineConfigAndDisplacement = engineConfigAndDisplacement;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setDriveWheels(String driveWheels) {
        this.driveWheels = driveWheels;
    }

    public void setGeneralizedWeight(double generalizedWeight) {
        this.generalizedWeight = generalizedWeight;
    }
}
