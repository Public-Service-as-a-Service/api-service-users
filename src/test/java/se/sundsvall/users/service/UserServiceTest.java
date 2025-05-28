package se.sundsvall.users.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.Problem;
import se.sundsvall.users.api.model.UpdateUserRequest;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.db.UserRepository;
import se.sundsvall.users.integration.db.model.Enum.Status;
import se.sundsvall.users.integration.db.model.UserEntity;
import se.sundsvall.users.service.Mapper.UserMapper;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepositoryMock;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UserService userService;

	@Test
	void getUserByEmail() {
		// Arrange
		final var email = "Test123@mail.com";
		final var userEntity = UserEntity.create().withPartyId(email);
		final var expectedUser = new UserResponse();

		when(userRepositoryMock.findByEmail(email)).thenReturn(Optional.of(userEntity));
		when(userMapper.toUserResponse(userEntity)).thenReturn(expectedUser);

		// Act
		final var result = userService.getUserByEmail(email);

		// Assert
		assertThat(result).isSameAs(expectedUser);
		verify(userRepositoryMock).findByEmail(email);
		verify(userMapper).toUserResponse(userEntity);

	}

	@Test
	void createUser() {
		// Arrange
		final var email = "Test@testmail.com";
		final var phoneNumber = "0701740669";
		final var municipalityId = "2281";
		final var status = "ACTIVE";

		// Build request and expected entities/responses
		var userRequest = UserRequest.create()
			.withEmail(email)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);

		var userEntity = UserEntity.create()
			.withEmail(email)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(Status.valueOf(status));

		var userResponse = UserResponse.create()
			.withEmail(email)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);

		when(userRepositoryMock.findByEmail(email)).thenReturn(Optional.empty());
		when(userRepositoryMock.save(userEntity)).thenReturn(userEntity);
		when(userMapper.toUserEntity(eq(userRequest), anyString())).thenReturn(userEntity);
		when(userMapper.toUserResponse(userEntity)).thenReturn(userResponse);

		// Act
		var created = userService.createUser(userRequest);

		// Assert
		// Verify we saved exactly that entity instance
		verify(userRepositoryMock).save(userEntity);

		assertThat(created).isNotNull();
		assertThat(created).isEqualTo(userResponse);
	}

	@Test
	void updateUser() {
		// Arrange
		final var email = "Test@testmail.se";
		final var phoneNumber = "0701740619";
		final var municipalityId = "2281";
		final var status = "ACTIVE";
		final var userRequestMock = UpdateUserRequest.create()
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);
		final var userEntity = UserEntity.create().withEmail(email)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(Status.valueOf(status));
		final var userResponseMock = UserResponse.create().withEmail(email)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);
		// Mock
		when(userRepositoryMock.findByEmail(email)).thenReturn(Optional.of(userEntity));

		when(userRepositoryMock.save(userEntity)).thenReturn(userEntity);
		when(userMapper.toUserResponse(userEntity)).thenReturn(userResponseMock);

		// Act
		final var updatedUser = userService.updateUserByEmail(userRequestMock, email);

		// Verify/Assert
		verify(userRepositoryMock).save(same(userEntity));
		assertThat(updatedUser).isNotNull();
		assertThat(updatedUser).isEqualTo(userResponseMock);
	}

	@Test
	void deleteUser() {

		// Arrange
		final var email = "Test@testmail.se";

		// Act
		userService.deleteUserByEmail(email);

		// Verify/Assert
		verify(userRepositoryMock).deleteByEmail(email);

	}

	@Test
	void createUserAlreadyExists() {
		// Arrange
		final var email = "Test@testmail.se";
		final var userRequest = new UserRequest();
		userRequest.setEmail(email);

		// Mock
		when(userRepositoryMock.findByEmail(email)).thenReturn(Optional.of(new UserEntity()));

		// Act & Assert
		final var exception = assertThrows(Throwable.class, () -> userService.createUser(userRequest));

		assertThat((exception))
			.isInstanceOf(Problem.class)
			.hasMessageContaining("user already exists");

		verify(userRepositoryMock).findByEmail(email);
		verify(userRepositoryMock, never()).save(any());
		verifyNoMoreInteractions(userRepositoryMock, userMapper);
	}

	@Test
	void getUserByEmailNotFound() {
		// Arrange
		final var email = "Test@testmail.com";

		when(userRepositoryMock.findByEmail(email)).thenReturn(Optional.empty());

		// Act
		final var exception = assertThrows(Throwable.class, () -> userService.getUserByEmail(email));
		// Assert
		assertThat(exception)
			.isInstanceOf(Problem.class)
			.hasMessageContaining("user " + email + " was not found");

		verify(userRepositoryMock).findByEmail(email);
		verify(userRepositoryMock, never()).getReferenceById(any());
		verify(userMapper, never()).toUserResponse(any());
		verifyNoMoreInteractions(userRepositoryMock, userMapper);
	}

	@Test
	void updateUserNotFound() {
		// Arrange
		final var email = "Test@testmail.se";
		final var request = UpdateUserRequest.create();

		// Mock
		when(userRepositoryMock.findByEmail(email)).thenReturn(Optional.empty());
		final var problem = assertThrows(Throwable.class, () -> userService.updateUserByEmail(request, email));

		// Assert
		assertThat(problem).hasMessage("Not Found: user " + email + " was not found");
		assertThat(problem).isNotNull();
	}

}
