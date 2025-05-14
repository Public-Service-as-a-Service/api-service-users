package se.sundsvall.users.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import se.sundsvall.users.api.model.UpdateUserRequest;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.db.UserRepository;
import se.sundsvall.users.integration.db.model.Enum.Status;
import se.sundsvall.users.integration.db.model.UserEntity;
import se.sundsvall.users.service.Mapper.UserMapper;

import static java.lang.String.format;
import static org.zalando.problem.Status.CONFLICT;
import static org.zalando.problem.Status.NOT_FOUND;

@Service
@Transactional
public class UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	private final String USER_NOT_FOUND = "user %s was not found";
	private final String USER_ALREADY_EXISTING = "user %s already exist";

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	// CREATE
	public UserResponse createUser(UserRequest userRequest) {
		if (userRepository.findByEmail(userRequest.getEmail()).isEmpty()) {
			final var userEntity = userRepository.save(userMapper.toUserEntity(userRequest));

			return userMapper.toUserResponse(userEntity);
		}
		throw Problem.valueOf(CONFLICT, format(USER_ALREADY_EXISTING, userRequest.getEmail()));
	}

	// READ
	public UserResponse getUserByEmail(String email) {
		return userRepository.findByEmail(email).map(userMapper::toUserResponse)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, format(USER_NOT_FOUND, email)));
	}

	// UPDATE
	public UserResponse updateUser(UpdateUserRequest userRequest, String email) {

		var userEntity = userRepository.findByEmail(email)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, format(USER_NOT_FOUND, email)));

		userEntity.setPhoneNumber(userRequest.getPhoneNumber());
		userEntity.setMunicipalityId(userRequest.getMunicipalityId());
		userEntity.setStatus(Status.valueOf(userRequest.getStatus().toUpperCase()));
		userRepository.save(userEntity);

		return userMapper.toUserResponse(userEntity);
	}

	// DELETE
	public void deleteUser(String email) {
		userRepository.deleteById(email);
	}
}
