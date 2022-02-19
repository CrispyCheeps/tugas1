package models;

public class Country {
    private String countryId;
    private String countryName;
    private int countryRegion;

    public Country() {

    }

    public Country(String countryId, String countryName, int countryRegion) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.countryRegion = countryRegion;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(int countryRegion) {
        this.countryRegion = countryRegion;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId='" + countryId + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryRegion=" + countryRegion +
                '}';
    }
}
