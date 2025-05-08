package se.sundsvall.users.integration.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
	ACTIVE,
	INACTIVE,
	SUSPENDED;

	@JsonCreator
	public static Status fromString(String value) {
		return value == null ? null : Status.valueOf(value.toUpperCase());
	}
}
