package se.sundsvall.users.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserResponse {

    @Schema(description = "Epost-adress", example = "kalle.kula@sundsvall.se")
    private String email;
    @Schema(description = "Telefonnummer", example = "0701234567")
    private String phoneNumber;
    @Schema(description = "Kommun-id", example = "2281")
    private String municipalityId;
    @Schema(description = "Stratus", example = "active")
    private String status;

    public static UserResponse create(){
        return new UserResponse();
    }

    public String getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(String municipalityId) {
        this.municipalityId = municipalityId;
    }

    public UserResponse withMunicipalityId(String municipalityId) {
        this.municipalityId = municipalityId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserResponse withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserResponse withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public UserResponse withStatus(String status) {
        this.status = status;
        return this;
    }
}
