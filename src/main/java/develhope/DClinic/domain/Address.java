package develhope.DClinic.domain;

public class Address {

    private String street;
    private String town;
    private String stNumber;
    private String zipCode;

    public Address() {
    }

    public Address(String street, String town, String stNumber, String zipCode) {
        this.street = street;
        this.town = town;
        this.stNumber = stNumber;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStNumber() {
        return stNumber;
    }

    public void setStNumber(String stNumber) {
        this.stNumber = stNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
