package se.sundsvall.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.ThrowableProblem;
import se.sundsvall.users.api.model.UpdateUserRequest;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.UserRepository;
import se.sundsvall.users.integration.model.UserEntity;
import se.sundsvall.users.service.Mapper.UserMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.zalando.problem.Status.CONFLICT;
import static org.zalando.problem.Status.NOT_FOUND;
import static se.sundsvall.users.service.Mapper.UserMapper.toUserEntity;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private ObjectMapper objectMapperMock;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById() {
        //Arrange
        final var email = "Test123!@mail.com";
        final var userEntity = UserEntity.create().withId(email.toString());
        final var expectedUser = new UserResponse();

        when(userRepositoryMock.findById(email)).thenReturn(Optional.of(userEntity));
        when(userRepositoryMock.getById(email.toString())).thenReturn((userEntity));

        try (MockedStatic<UserMapper> mapperMock = Mockito.mockStatic(UserMapper.class)) {
            mapperMock.when(() -> UserMapper.toUserResponse(any())).thenReturn(expectedUser);

            //Act
            final var result = userService.getUserByID(email);

            //Assert
            assertThat(result).isSameAs(expectedUser);
            verify(userRepositoryMock).findById(email);
            verify(userRepositoryMock).getById(email.toString());
            mapperMock.verify(() -> UserMapper.toUserResponse(userEntity));
        }
    }


    @Test
    void createUser() {
        //Arrange
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

        when(userRepositoryMock.save(any())).thenReturn(userEntity);
        when(userRepositoryMock.findById(id)).thenReturn(Optional.empty());

        try (MockedStatic<UserMapper> mapperMock = Mockito.mockStatic(UserMapper.class)) {
            mapperMock.when(() -> UserMapper.toUserEntity(any())).thenReturn(userEntity);
            mapperMock.when(() -> UserMapper.toUserResponse(userEntity)).thenReturn(userResponseMock);

            //Act
            final var createdUser = userService.createUser(userRequestMock);

            //Assert
            verify(userRepositoryMock).save(same(userEntity));
            assertThat(createdUser).isNotNull();
            assertThat(createdUser).isEqualTo(userResponseMock);
            //assertThat(userRequestMock).isEqualTo(userEntity);
            //assertThat(createdUser).isEqualTo(userEntity);
        }
    }
}