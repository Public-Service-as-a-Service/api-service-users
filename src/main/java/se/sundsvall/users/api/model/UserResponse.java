package se.sundsvall.users.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserResponse {

    @Schema(description = "Epost-adress", example = "kalle.kula@sundsvall.se")
    private String email;
    @Schema(description = "Telefonnummer", example = "0701234567")
    private String phoneNumber;
    @Schema(description = "Kommun-id", example = "2281")
    public String municipalityId;

    public String getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(String municipalityId) {
        this.municipalityId = municipalityId;
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
}
