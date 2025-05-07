package se.sundsvall.users.integration.model.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum Status {
	ACTIVE,
	INACTIVE,
	SUSPENDED;

}
