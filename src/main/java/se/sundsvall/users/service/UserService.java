package se.sundsvall.users.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import se.sundsvall.users.api.model.UpdateUserRequest;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.UserRepository;
import se.sundsvall.users.service.Mapper.UserMapper;

import static java.lang.String.format;
import static org.zalando.problem.Status.CONFLICT;
import static org.zalando.problem.Status.NOT_FOUND;
import static se.sundsvall.users.service.Mapper.UserMapper.toUserEntity;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CREATE
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.findById(userRequest.getEmail()).isEmpty()){
            final var userEntity = userRepository.save(toUserEntity(userRequest));

            UserMapper userMapper = new UserMapper();
            return userMapper.toUserResponse(userEntity);
        }
        throw Problem.valueOf(CONFLICT, format("user %s already exist", userRequest.getEmail()));
    }

    //READ
    public UserResponse getUserByID(String id) {
        if (userRepository.findById(id).isPresent()) {
            var userEntity = userRepository.getById(id);

            UserMapper userMapper = new UserMapper();
            return userMapper.toUserResponse(userEntity);
        }
        throw Problem.valueOf(NOT_FOUND, format("user %s was not found", id));
    }

    //UPDATE
    public UserResponse updateUser(UpdateUserRequest userRequest, String email) {
        if (userRepository.findById(email).isPresent()) {
            var userEntity = userRepository.getById(email);

            userEntity.setPhoneNumber(userRequest.getPhoneNumber());
            userEntity.setMunicipalityId(userRequest.getMunicipalityId());
            userEntity.setStatus(userRequest.getStatus());
            userRepository.save(userEntity);

            UserMapper userMapper = new UserMapper();
            return userMapper.toUserResponse(userEntity);
        }
        throw Problem.valueOf(NOT_FOUND, format("user %s was not found", email));
    }

    //DELETE
    public UserResponse deleteUser(String id){
        if (userRepository.findById(id).isPresent()) {
            var userEntity = userRepository.getById(id);
            userRepository.deleteById(id);

            UserMapper userMapper = new UserMapper();
            return userMapper.toUserResponse(userEntity);
        }
        throw Problem.valueOf(NOT_FOUND, format("user with %s was not found", id));
    }
}
