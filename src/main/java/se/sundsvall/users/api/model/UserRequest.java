package se.sundsvall.users.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;

public class UserRequest {

	@Schema(description = "Epost-adress", example = "kalle.kula@sundsvall.se")
	@Email(message = "must be a valid Email-adress")
	@NotBlank
	private String email;

	@Schema(description = "Telefonnummer", example = "0701234567")
	@NotBlank(message = "must be a Phone-number")
	@Pattern(regexp = "^\\+?[0-9 ()-]{7,20}$")
	private String phoneNumber;

	@Schema(description = "Kommun", example = "2281")
	@NotBlank(message = "must be a Municipality-ID")
	@ValidMunicipalityId
	private String municipalityId;

	@Schema(description = "Status", example = "ACTIVE")
	@NotBlank(message = "status must be Active, Suspended or Inactive")
	private String status;

	public static UserRequest create() {
		return new UserRequest();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRequest withEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserRequest withPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public String getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(String municipalityId) {
		this.municipalityId = municipalityId;
	}

	public UserRequest withMunicipalityId(String municipalityId) {
		this.municipalityId = municipalityId;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserRequest withStatus(String status) {
		this.status = status;
		return this;
	}
}
