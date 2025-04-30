package se.sundsvall.users.api.model;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import se.sundsvall.users.integration.model.UserEntity;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.AllOf.allOf;

public class UpdateUserRequestTest {

	@Test
	void testBean() {
		MatcherAssert.assertThat(UserEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters()));
	}

	@Test
	void testBuildMethod() {
		final var phoneNumber = "phoneNumber";
		final var municipalityId = "municipalityId";
		final var status = "status";

		final var userEntity = UserEntity.create()
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);

		assertThat(userEntity.getPhoneNumber()).isEqualTo(phoneNumber);
		assertThat(userEntity.getMunicipalityId()).isEqualTo(municipalityId);
		assertThat(userEntity.getStatus()).isEqualTo(status);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(UserResponse.create()).hasAllNullFieldsOrProperties();
	}
}
