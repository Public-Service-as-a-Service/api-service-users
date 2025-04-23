package se.sundsvall.users.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import se.sundsvall.users.api.UserResource;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.integration.UserRepository;
import se.sundsvall.users.integration.model.UserEntity;
import se.sundsvall.users.service.Mapper.UserMapper;

import java.util.HashMap;
import java.util.Map;

import static se.sundsvall.users.service.Mapper.UserMapper.toUserEntity;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CREATE
    public void createUser(UserRequest userRequest) {
       final var userEntity = userRepository.save(toUserEntity(userRequest));
    }

    //READ
    public UserRequest getUserByID(String id) {
        var userEntity = userRepository.getById(id);
        UserMapper userMapper = new UserMapper();
        return userMapper.toUserRequest(userEntity);
    }

    //UPDATE
    public UserEntity updateUser(UserRequest userRequest, String email) {
       var userEntity = userRepository.getById(email);

       userEntity.setPhoneNumber(userRequest.getPhoneNumber());
       userEntity.setMunicipalityId(userRequest.getMunicipalityId());
       userEntity.setStatus(userRequest.getStatus());
       userRepository.save(userEntity);

       return null;
    }

    //DELETE
    public UserEntity deleteUser(String id){
        userRepository.deleteById(id);
        return null;
    }
}
