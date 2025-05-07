package se.sundsvall.users.service.Mapper;

import jdk.jshell.Snippet;
import org.springframework.stereotype.Component;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.model.Enum.Status;
import se.sundsvall.users.integration.model.UserEntity;

import java.util.Optional;

@Component
public class UserMapper {

	public UserResponse toUserResponse(final UserEntity user) {
		return Optional.ofNullable(user)
			.map(entity -> UserResponse.create()
				.withEmail(entity.getEmail())
				.withPhoneNumber(entity.getPhoneNumber())
				.withMunicipalityId(entity.getMunicipalityId())
				.withStatus(entity.getStatus()))
			.orElse(null);
	}

	public UserEntity toUserEntity(UserRequest userRequest) {
		return Optional.ofNullable(userRequest)
			.map(request -> UserEntity.create()
				.withEmail(request.getEmail())
				.withPhoneNumber(request.getPhoneNumber())
				.withMunicipalityId(request.getMunicipalityId())
				.withStatus(request.getStatus()))
			.orElse(null);
	}
}
