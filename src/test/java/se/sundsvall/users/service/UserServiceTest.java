package se.sundsvall.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.users.api.model.UpdateUserRequest;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.UserRepository;
import se.sundsvall.users.integration.model.UserEntity;
import se.sundsvall.users.service.Mapper.UserMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

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
		final var userEntity = UserEntity.create().withId(email);
		final var expectedUser = new UserResponse();

		when(userRepositoryMock.findById(email)).thenReturn(Optional.of(userEntity));
		when(userRepositoryMock.getReferenceById(email)).thenReturn(userEntity);
		when(userMapper.toUserResponse(userEntity)).thenReturn(expectedUser);

		// Act
		final var result = userService.getUserByEmail(email);

		// Assert
		assertThat(result).isSameAs(expectedUser);
		verify(userRepositoryMock).findById(email);
		verify(userRepositoryMock).getReferenceById(email);
		verify(userMapper).toUserResponse(userEntity);

	}

	@Test
	void createUser() {
		// Arrange
		final var id = "TestMail123@mail.se";
		final var phoneNumber = "99070121212";
		final var municipalityId = "2281";
		final var status = "Active";
		final var userRequestMock = UserRequest.create().withEmail(id)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);
		final var userEntity = UserEntity.create().withEmail(id)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);
		final var userResponseMock = UserResponse.create().withEmail(id)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);

		when(userRepositoryMock.save(userEntity)).thenReturn(userEntity);
		when(userRepositoryMock.findById(id)).thenReturn(Optional.empty());

		when(userMapper.toUserEntity(userRequestMock)).thenReturn(userEntity);
		when(userMapper.toUserResponse(userEntity)).thenReturn(userResponseMock);

		// Act
		final var createdUser = userService.createUser(userRequestMock);

		// Assert
		verify(userRepositoryMock).save(same(userEntity));
		assertThat(createdUser).isNotNull();
		assertThat(createdUser).isEqualTo(userResponseMock);
		// assertThat(userRequestMock).isEqualTo(userEntity);
		// assertThat(createdUser).isEqualTo(userEntity);

	}
	@Test
	void updateUserNotFound() {
		// Arrange
		final var email = "TestMail123@mail.se";
		final var requet = UpdateUserRequest.create();

		//Mock
		when(userRepositoryMock.findById(email)).thenReturn(Optional.empty());
		final var problem = assertThrows(Throwable.class, () -> userService.updateUser(requet, email));

		// Assert
		assertThat(problem).hasMessage("Not Found: user " + email + " was not found");
		assertThat(problem).isNotNull();
	}
	@Test
	void deleteUserNotFound() {
		// Arrange
		final var email = "TestMail123@mail.se";

		// Mock
		when(userRepositoryMock.findById(email)).thenReturn(Optional.empty());
		final var problem = assertThrows(Throwable.class, () -> userService.deleteUser(email));

		// Assert
		assertThat(problem).hasMessage("Not Found: user " + email + " was not found");
		assertThat(problem).isNotNull();
	}
}
