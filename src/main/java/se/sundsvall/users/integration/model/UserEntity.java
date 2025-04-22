package se.sundsvall.users.integration.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "guid")
    @UuidGenerator
    private String id;
    @Id
    @Column(name = "email-address")
    private String email;

    @Column(name = "phone-number")
    private String phoneNumber;

    @Column(name = "municipality-id")
    private String municipalityId;

    @Column(name = "status", columnDefinition = "ENUM")
    private String status;

    public UserEntity() {}
    public UserEntity(String id, String email, String phoneNumber, String municipalityId, String status) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.municipalityId = municipalityId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(String municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
