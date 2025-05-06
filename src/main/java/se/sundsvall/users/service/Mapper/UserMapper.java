package se.sundsvall.users.service.Mapper;

import org.springframework.stereotype.Component;
import se.sundsvall.users.api.model.User;
import se.sundsvall.users.integration.model.UserEntity;

import java.util.Optional;

@Component
public class UserMapper {

	public User toUserResponse(final UserEntity user) {
		return Optional.ofNullable(user)
			.map(entity -> User.create()
				.withEmail(entity.getEmail())
				.withPhoneNumber(entity.getPhoneNumber())
				.withMunicipalityId(entity.getMunicipalityId())
				.withStatus(entity.getStatus()))
			.orElse(null);
	}

	public UserEntity toUserEntity(User userRequest, String email) {
		return Optional.ofNullable(userRequest)
			.map(request -> UserEntity.create()
				.withEmail(email)
				.withPhoneNumber(request.getPhoneNumber())
				.withMunicipalityId(request.getMunicipalityId())
				.withStatus(request.getStatus()))
			.orElse(null);
	}
}
