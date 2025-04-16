package se.sundsvall.users.integration.model;

public class UserEntity {

    private int id;
    private String email;
    private int phoneNumber;
    private int municipalityId;
    private boolean status;

    public UserEntity() {}
    public UserEntity(int id, String email, int phoneNumber, int municipalityId, boolean status) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.municipalityId = municipalityId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
