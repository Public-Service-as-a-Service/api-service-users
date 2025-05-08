package se.sundsvall.users.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import se.sundsvall.dept44.common.validators.annotation.ValidMobileNumber;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.users.integration.model.Status;

public class UpdateUserRequest {

	@Schema(description = "Telefonnummer", example = "0701234567")
	@NotBlank(message = "must be a Phone-number")
	// @Pattern(regexp = "^\\+?[0-9 ()-]{7,20}$")
	@ValidMobileNumber
	private String phoneNumber;

	@Schema(description = "Kommun", example = "2281")
	@NotBlank(message = "must be a Municipality-ID")
	@ValidMunicipalityId
	private String municipalityId;

	@Schema(description = "Status", example = "ACTIVE")
	@Enumerated
	private Status status;

	public static UpdateUserRequest create() {
		return new UpdateUserRequest();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UpdateUserRequest withPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public String getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(String municipalityId) {
		this.municipalityId = municipalityId;
	}

	public UpdateUserRequest withMunicipalityId(String municipalityId) {
		this.municipalityId = municipalityId;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UpdateUserRequest withStatus(Status status) {
		this.status = status;
		return this;
	}
}
