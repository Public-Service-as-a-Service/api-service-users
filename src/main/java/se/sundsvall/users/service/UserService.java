package se.sundsvall.users.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import se.sundsvall.users.api.UserResource;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.UserRepository;
import se.sundsvall.users.integration.model.UserEntity;
import se.sundsvall.users.service.Mapper.UserMapper;

import java.util.HashMap;
import java.util.Map;

import static se.sundsvall.users.service.Mapper.UserMapper.toUserEntity;


@Service
@Transactional
public class UserService {
    private final Map<Long, UserEntity> testDatabase = new HashMap<>();
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    //CREATE
    //@PostMapping("user/{userId}")
    public void createUser(UserRequest userRequest) {

        final var userEntity = userRepository.save(toUserEntity(userRequest));
    }

    //READ
    public UserEntity getUser() {
        return null;
    }


    public UserRequest getUserByID(String id) {
        var userEntity = userRepository.getById(id);
        UserMapper userMapper = new UserMapper();
        return userMapper.toUserRequest(userEntity);
    }

    //UPDATE
    //@PutMapping("user/{userId}")
    public UserEntity updateUser(String id, String email, String phoneNumber, String municipalityId, boolean status) {

        return null;
    }

    //DELETE
    //@DeleteMapping
    public UserEntity deleteUser(Long id){
        testDatabase.remove(id);
        return null;
    }
}
