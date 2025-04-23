package se.sundsvall.users.service.Mapper;

import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.integration.model.UserEntity;

public class UserMapper {

    public UserRequest toUserRequest(UserEntity user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(user.getEmail());
        userRequest.setPhoneNumber(user.getPhoneNumber());
        userRequest.setMunicipalityId(user.getMunicipalityId());
        return userRequest;
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
