package se.sundsvall.users.integration.model;


public class UserEntity {

    private long id;
    private String email;
    private String phoneNumber;
    private int municipalityId;
    private boolean status;

    public UserEntity() {}
    public UserEntity(long id, String email, String phoneNumber, int municipalityId, boolean status) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.municipalityId = municipalityId;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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
