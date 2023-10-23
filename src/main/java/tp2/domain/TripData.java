package tp2.domain;

public class TripData {
    private double dayNum;
    private String trip;
    private long timestamp;
    private double latitude;
    private double longitude;
    private double vehicleSpeed;
    private double maf;
    private String engineRPM;
    private double absoluteLoad;
    private double outsideAirTemp;
    private double fuelRate;
    private double acPowerKW;
    private double acPowerWatts;
    private double heaterPowerWatts;
    private double hvBatteryCurrent;
    private double hvBatterySOC;
    private double hvBatteryVoltage;
    private double shortTermFuelTrimBank1;
    private double shortTermFuelTrimBank2;
    private double longTermFuelTrimBank1;
    private double longTermFuelTrimBank2;
    private String vehID;

    public TripData(double dayNum, String vehID, String trip, long timestamp, double latitude, double longitude, double vehicleSpeed,
                    double maf, String engineRPM, double absoluteLoad, double outsideAirTemp, double fuelRate,
                    double acPowerKW, double acPowerWatts, double heaterPowerWatts, double hvBatteryCurrent,
                    double hvBatterySOC, double hvBatteryVoltage, double shortTermFuelTrimBank1,
                    double shortTermFuelTrimBank2, double longTermFuelTrimBank1, double longTermFuelTrimBank2) {
        this.dayNum = dayNum;
        this.vehID = vehID;
        this.trip = trip;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicleSpeed = vehicleSpeed;
        this.maf = maf;
        this.engineRPM = engineRPM;
        this.absoluteLoad = absoluteLoad;
        this.outsideAirTemp = outsideAirTemp;
        this.fuelRate = fuelRate;
        this.acPowerKW = acPowerKW;
        this.acPowerWatts = acPowerWatts;
        this.heaterPowerWatts = heaterPowerWatts;
        this.hvBatteryCurrent = hvBatteryCurrent;
        this.hvBatterySOC = hvBatterySOC;
        this.hvBatteryVoltage = hvBatteryVoltage;
        this.shortTermFuelTrimBank1 = shortTermFuelTrimBank1;
        this.shortTermFuelTrimBank2 = shortTermFuelTrimBank2;
        this.longTermFuelTrimBank1 = longTermFuelTrimBank1;
        this.longTermFuelTrimBank2 = longTermFuelTrimBank2;
    }

    public String getVehId() {
        return vehID;
    }


    public double getDayNum() {
        return dayNum;
    }

    public String getTrip() {
        return trip;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getVehicleSpeed() {
        return vehicleSpeed;
    }

    public double getMaf() {
        return maf;
    }

    public String getEngineRPM() {
        return engineRPM;
    }

    public double getAbsoluteLoad() {
        return absoluteLoad;
    }

    public double getOutsideAirTemp() {
        return outsideAirTemp;
    }

    public double getFuelRate() {
        return fuelRate;
    }

    public double getAcPowerKW() {
        return acPowerKW;
    }

    public double getAcPowerWatts() {
        return acPowerWatts;
    }

    public double getHeaterPowerWatts() {
        return heaterPowerWatts;
    }

    public double getHvBatteryCurrent() {
        return hvBatteryCurrent;
    }

    public double getHvBatterySOC() {
        return hvBatterySOC;
    }

    public double getHvBatteryVoltage() {
        return hvBatteryVoltage;
    }

    public double getShortTermFuelTrimBank1() {
        return shortTermFuelTrimBank1;
    }

    public double getShortTermFuelTrimBank2() {
        return shortTermFuelTrimBank2;
    }

    public double getLongTermFuelTrimBank1() {
        return longTermFuelTrimBank1;
    }

    public double getLongTermFuelTrimBank2() {
        return longTermFuelTrimBank2;
    }

    // Métodos setters

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setVehicleSpeed(double vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;
    }

    public void setMaf(double maf) {
        this.maf = maf;
    }

    public void setEngineRPM(String engineRPM) {
        this.engineRPM = engineRPM;
    }

    public void setAbsoluteLoad(double absoluteLoad) {
        this.absoluteLoad = absoluteLoad;
    }

    public void setOutsideAirTemp(double outsideAirTemp) {
        this.outsideAirTemp = outsideAirTemp;
    }

    public void setFuelRate(double fuelRate) {
        this.fuelRate = fuelRate;
    }

    public void setAcPowerKW(double acPowerKW) {
        this.acPowerKW = acPowerKW;
    }

    public void setAcPowerWatts(double acPowerWatts) {
        this.acPowerWatts = acPowerWatts;
    }

    public void setHeaterPowerWatts(double heaterPowerWatts) {
        this.heaterPowerWatts = heaterPowerWatts;
    }

    public void setHvBatteryCurrent(double hvBatteryCurrent) {
        this.hvBatteryCurrent = hvBatteryCurrent;
    }

    public void setHvBatterySOC(double hvBatterySOC) {
        this.hvBatterySOC = hvBatterySOC;
    }

    public void setHvBatteryVoltage(double hvBatteryVoltage) {
        this.hvBatteryVoltage = hvBatteryVoltage;
    }

    public void setShortTermFuelTrimBank1(double shortTermFuelTrimBank1) {
        this.shortTermFuelTrimBank1 = shortTermFuelTrimBank1;
    }

    public void setShortTermFuelTrimBank2(double shortTermFuelTrimBank2) {
        this.shortTermFuelTrimBank2 = shortTermFuelTrimBank2;
    }

    public void setLongTermFuelTrimBank1(double longTermFuelTrimBank1) {
        this.longTermFuelTrimBank1 = longTermFuelTrimBank1;
    }

    public void setLongTermFuelTrimBank2(double longTermFuelTrimBank2) {
        this.longTermFuelTrimBank2 = longTermFuelTrimBank2;
    }

    public void setVehId(String vehID) {
        this.vehID = vehID;
    }
}
