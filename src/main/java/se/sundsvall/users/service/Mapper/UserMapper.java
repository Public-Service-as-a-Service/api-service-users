package se.sundsvall.users.service.Mapper;

import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.model.UserEntity;

import java.util.Optional;

public class UserMapper {

    public static UserResponse toUserResponse(final UserEntity user) {
        return Optional.ofNullable(user)
                .map(r -> UserResponse.create()
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
