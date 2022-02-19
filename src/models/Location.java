package models;

public class Location {
    int locationId;
    String alamat;
    String kodePos;
    String city;
    String provinsi;
    String country;

    public Location() {

    }

    public Location(int locationId, String alamat, String kodePos, String city, String provinsi, String country) {
        this.locationId = locationId;
        this.alamat = alamat;
        this.kodePos = kodePos;
        this.city = city;
        this.provinsi = provinsi;
        this.country = country;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", alamat='" + alamat + '\'' +
                ", kodePos='" + kodePos + '\'' +
                ", city='" + city + '\'' +
                ", provinsi='" + provinsi + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
