package se.sundsvall.users.integration.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Column(name = "guid")
    private long id;
    @Id
    @Column(name = "email-address")
    private String email;

    @Column(name = "phone-number")
    private String phoneNumber;

    @Column(name = "municipality-id")
    private int municipalityId;

    @Column(name = "status")
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
