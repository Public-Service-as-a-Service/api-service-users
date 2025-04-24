package se.sundsvall.users.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;

public class UpdateUserRequest {

    @Schema(description = "Telefonnummer", example = "0701234567")
    @NotBlank(message = "must be a Phone-number")
    @Pattern(regexp = "^\\+?[0-9 ()-]{7,20}$")
    private String phoneNumber;

    @Schema(description = "Kommun", example = "2281")
    @NotBlank(message = "must be a Municipality-ID")
    @ValidMunicipalityId
    private String municipalityId;

    @Schema(description = "Status", example = "ACTIVE   ")
    @NotBlank(message = "Status must be Active, Suspended or Inactive")
    private String status;

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
