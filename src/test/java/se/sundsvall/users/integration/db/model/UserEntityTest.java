package se.sundsvall.users.integration.db.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;

public class UserEntityTest {

	@Test
	void testBean() {
		assertThat(UserEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters()));
	}

	@Test
	void testBuildMethod() {
		final var guid = UUID.randomUUID().toString();
		final var email = "email";
		final var phoneNumber = "phoneNumber";
		final var municipalityId = "municipalityId";
		final var status = "status";

		final var userEntity = UserEntity.create()
			.withId(guid)
			.withEmail(email)
			.withPhoneNumber(phoneNumber)
			.withMunicipalityId(municipalityId)
			.withStatus(status);

		assertThat(userEntity.getId()).isEqualTo(guid);
		assertThat(userEntity.getEmail()).isEqualTo(email);
		assertThat(userEntity.getPhoneNumber()).isEqualTo(phoneNumber);
		assertThat(userEntity.getMunicipalityId()).isEqualTo(municipalityId);
		assertThat(userEntity.getStatus()).isEqualTo(status);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(UserEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new UserEntity()).hasAllNullFieldsOrProperties();
	}
}
