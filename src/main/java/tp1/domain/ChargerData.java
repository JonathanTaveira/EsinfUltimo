package tp1.domain;

public class ChargerData {
    private String supercharger;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String country;
    private int stalls;
    private int kW;
    private String gps;
    private String elevm;
    private String status;

    public ChargerData(String supercharger, String streetAddress, String city, String state, String zip, String country, int stalls, int kW, String gps, String elevm, String status) {
        this.supercharger = supercharger;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.stalls = stalls;
        this.kW = kW;
        this.gps = gps;
        this.elevm = elevm;
        this.status = status;
    }

    public String getSupercharger() {
        return supercharger;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public int getStalls() {
        return stalls;
    }

    public int getkW() {
        return kW;
    }

    public String getGps() {
        return gps;
    }

    public String getElevm() {
        return elevm;
    }

    public String getStatus() {
        return status;
    }
}
