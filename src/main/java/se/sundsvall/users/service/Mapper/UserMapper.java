package se.sundsvall.users.service.Mapper; //TODO paketnamn bör vara med gemener, dvs se.sundsvall.users.service.mapper

import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.model.UserEntity;

import java.util.Optional;

// TODO Gör antingen klassen final eller annotera med @Component och ta bort "static" från metoderna
// Annars bra separering av DTO och entitet 👍
public final class UserMapper {

	public static UserResponse toUserResponse(final UserEntity user) {
		return Optional.ofNullable(user)
			.map(r -> UserResponse.create() // TODO petitess men kan vara bra att döpa "r-" -> "entity" eller "userEntity"
				.withEmail(r.getEmail())
				.withPhoneNumber(r.getPhoneNumber())
				.withMunicipalityId(r.getMunicipalityId())
				.withStatus(r.getStatus()))
			.orElse(null);
	}

	public static UserEntity toUserEntity(UserRequest userRequest) {
		return Optional.ofNullable(userRequest)
			.map(r -> UserEntity.create()
				.withEmail(r.getEmail())
				.withPhoneNumber(r.getPhoneNumber())
				.withMunicipalityId(r.getMunicipalityId())
				.withStatus(r.getStatus()))
			.orElse(null);
	}
}
