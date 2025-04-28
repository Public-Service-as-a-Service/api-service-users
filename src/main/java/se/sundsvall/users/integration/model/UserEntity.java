package se.sundsvall.users.integration.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.UuidGenerator;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;

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
    @NotBlank(message = "Email-adress måste anges")
    @Email(message = "ej en giltig Email-adress")
    private String email;

    @Column(name = "phone-number")
    @Pattern(regexp = "^\\+?[0-9 ()-]{7,20}$")
    @NotBlank
    private String phoneNumber;

    @Column(name = "municipality-id")
    @ValidMunicipalityId
    @NotBlank
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

    public static UserEntity create(){
        return new UserEntity();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity withId(final String id){
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEntity withEmail(String email){
        setEmail(email);
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserEntity withPhoneNumber(String phoneNumber){
        setPhoneNumber(phoneNumber);
        return this;
    }

    public String getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(String municipalityId) {
        this.municipalityId = municipalityId;
    }

    public UserEntity withMunicipalityId(String municipalityId){
        setMunicipalityId(municipalityId);
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserEntity withStatus(String status){
        setStatus(status);
        return this;
    }
}
