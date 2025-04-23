package se.sundsvall.users.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.sundsvall.users.integration.model.UserEntity;

public class UserRequest {

    @Schema(description = "Epost-adress", example = "kalle.kula@sundsvall.se")
    private String email;
    @Schema(description = "Telefonnummer", example = "0701234567")
    @NotBlank(message = "måste innehålla telefonnummer")
    private String phoneNumber;
    @Schema(description = "Kommun", example = "2281")
    @NotBlank(message = "måste innehålla kommunid")
    private String municipalityId;
    @Schema(description = "Status", example = "ACTIVE   ")
    @NotBlank(message = "måste innehålla  status")
    private String status;

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

    public String getStatus() { return status;}

    public void setStatus(String status) { this.status = status; }
}
