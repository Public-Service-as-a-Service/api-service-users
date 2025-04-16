package se.sundsvall.users.service;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sundsvall.users.api.UserResource;
import se.sundsvall.users.api.model.CreateUserRequest;
import se.sundsvall.users.integration.model.UserEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
@Service
public class UserService {
    private final Map<Long, UserEntity> testDatabase = new HashMap<>();

    public UserService() {
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setEmail("kalle.kula@sundsvall.se");
        user1.setPhoneNumber(701234567);
        user1.setMunicipalityId(2188);
        user1.setStatus(false);
        testDatabase.put(1L, user1);

        UserEntity user2 = new UserEntity();
        user2.setId(1L);
        user2.setEmail("tommy.tomat@sundsvall.se");
        user2.setPhoneNumber(707654321);
        user2.setMunicipalityId(2188);
        user2.setStatus(true);
        testDatabase.put(2L, user2);
    }


    //CREATE
    public UserEntity createUser(UserEntity userEntity){
        return userEntity;
    }

    //READ
    public UserEntity getUser() {
        return null;
    }

    @GetMapping("users/1")
    public UserEntity getUserByID(Long id) {
        UserEntity user = testDatabase.get(id);
        return user;
    }

    //UPDATE
    public UserEntity updateUser(){
        return null;
    }

    //DELETE
    public UserEntity deleteUser(){

        return null;
    }
}
