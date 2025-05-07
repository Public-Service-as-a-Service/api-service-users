package se.sundsvall.users.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.aspectj.bridge.Message;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ResponseBody;
import se.sundsvall.dept44.common.validators.annotation.ValidMobileNumber;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.users.integration.model.Status;

import java.lang.annotation.Documented;
import java.util.Objects;

public class UserRequest {

	@Schema(description = "Epost-adress", example = "kalle.kula@sundsvall.se")
	@Email(message = "must be a valid Email-adress")
	@NotBlank
	private String email;

	@Schema(description = "Telefonnummer", example = "0701234567")
	@NotBlank(message = "must be a Phone-number")
//	@Pattern(regexp = "^\\+?[0-9 ()-]{7,20}$", message = must be in format xxx-xxxxxxx)
	@ValidMobileNumber
	private String phoneNumber;

	@Schema(description = "Kommun", example = "2281")
	@NotBlank(message = "must be a Municipality-ID")
	@ValidMunicipalityId
	private String municipalityId;

	@Schema(description = "Status", example = "ACTIVE")
	// (message = "must be either ACTIVE, INACTIVE or SUSPENDED")
	@Enumerated
	private Status status;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UserRequest withStatus(Status status) {
		this.status = status;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(municipalityId, email, phoneNumber, status);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserRequest that = (UserRequest) o;
		return Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber)
			&& Objects.equals(status, that.status) && Objects.equals(municipalityId, that.municipalityId);
	}
}
