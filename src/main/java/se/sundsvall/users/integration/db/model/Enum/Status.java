package se.sundsvall.users.integration.db.model.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum Status {
	ACTIVE,
	INACTIVE,
	SUSPENDED;

	@JsonCreator
	public static Status fromString(String value) {
		return value == null ? null : Status.valueOf(value.toUpperCase());
	}
}
