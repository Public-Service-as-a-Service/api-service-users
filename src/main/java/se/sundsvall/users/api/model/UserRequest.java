package se.sundsvall.users.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import se.sundsvall.dept44.common.validators.annotation.ValidMobileNumber;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.users.api.validation.ValidEnum;
import se.sundsvall.users.integration.db.model.Enum.Status;
import java.util.Objects;

public class UserRequest {

	@Schema(description = "Epost-adress", example = "kalle.kula@sundsvall.se")
	@Email(message = "must be a valid Email-adress")
	@NotBlank(message = "cannot be blank")
	private String email;

	@Schema(description = "Telefonnummer", example = "0701234567")
	@NotBlank(message = "cannot be blank")
	@ValidMobileNumber(message = "must be a valid mobile number")
	private String phoneNumber;

	@Schema(description = "Kommun", example = "2281")
	@NotBlank(message = "cannot be blank")
	@ValidMunicipalityId(message = "must be a valid Municipality-ID")
	private String municipalityId;

	@Schema(description = "Status", example = "ACTIVE")
	@ValidEnum(message = "must be ACTIVE, INACTIVE or SUSPENDED", enumClass = Status.class, ignoreCase = true)
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
