package se.sundsvall.users.integration.db.model.Enum;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum Status {
	ACTIVE,
	INACTIVE,
	SUSPENDED;
}
