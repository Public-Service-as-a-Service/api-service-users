package se.sundsvall.users.service.Mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.integration.model.Enum.Status;
import se.sundsvall.users.integration.model.UserEntity;
import se.sundsvall.users.service.Mapper.UserMapper;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

	@InjectMocks
	UserMapper userMapper;

	@Test
	void toUserResponse() {
		// Arrange
		final var email = "TestMail123@mail.se";
		final var phoneNumber = "99070121212";
		final var municipalityId = "2281";
		final var status = "Active";

		final var userEntity = UserEntity.create().withEmail(email)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);

		// Act
		final var result = userMapper.toUserResponse(userEntity);

		// Assert
		assertThat(result).isNotNull();
		assertThat(result.getEmail()).isEqualTo(email);
		assertThat(result.getPhoneNumber()).isEqualTo(phoneNumber);
		assertThat(result.getMunicipalityId()).isEqualTo(municipalityId);
		assertThat(result.getStatus()).isEqualTo(status);
	}

	@Test
	void toUserEntity() {
		// Arrange
		final var email = "TestMail123@mail.se";
		final var phoneNumber = "99070121212";
		final var municipalityId = "2281";
		final var status = "Active";

		final var userRequest = UserRequest.create().withEmail(email)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);
		// Act
		final var result = userMapper.toUserEntity(userRequest);
		// Assert
		assertThat(result).isNotNull();
		assertThat(result.getEmail()).isEqualTo(email);
		assertThat(result.getPhoneNumber()).isEqualTo(phoneNumber);
		assertThat(result.getMunicipalityId()).isEqualTo(municipalityId);
		assertThat(result.getStatus()).isEqualTo(status);
	}

//    @Test
//    void toUserEntityWhenNull() {
//    }
}
