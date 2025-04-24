package se.sundsvall.users.service.Mapper;

import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.model.UserEntity;

public class UserMapper {

    public UserResponse toUserResponse(UserEntity user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setMunicipalityId(user.getMunicipalityId());
        return userResponse;
    }
    public static UserEntity toUserEntity(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setMunicipalityId(userRequest.getMunicipalityId());
        userEntity.setStatus(userRequest.getStatus());
        return userEntity;
    }
}
