package se.sundsvall.users.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserRequest {

    @Schema(description = "Epost-adress", example = "kalle.kula@sundsvall.se")
    private String email;
    @Schema(description = "Telefonnummer", example = "0701234567")
    private int phoneNumber;
    @Schema(description = "Kommun", example = "2281")
    public int municipalityId;

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
}
