package se.sundsvall.users.integration.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import se.sundsvall.users.integration.db.model.UserEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("junit")
@Sql(scripts = {
	"/db/script/truncate.sql",
	"/db/script/UserRepositoryTest.sql"
})
public class UserRepositoryTest {

	private static final String MAIL_ADRESS_1 = "testmail1@sundsvall.se";
	private static final String MAIL_ADRESS_2 = "testmail2@sundsvall.se";
	private static final String PHONE_NUMBER_1 = "0011234567";
	private static final String PHONE_NUMBER_2 = "0021234567";
	private static final String MUNICIPALITY_ID_1 = "2281";
	private static final String MUNICIPALITY_ID_2 = "0689";
	private static final String STATUS_1 = "ACTIVE";
	private static final String STATUS_2 = "SUSPENDED";

	@Autowired
	private UserRepository userRepository;

	@Test
	void createUser() {
		final var userEntity = UserEntity.create().withId(UUID.randomUUID().toString()).withEmail("TestMail123@hotmail.com").withPhoneNumber("0031234567").withMunicipalityId("2281").withStatus("INACTIVE");

		final var savedEntity = userRepository.save(userEntity);
		final var parsedEntity = userRepository.findById(savedEntity.getEmail());

		assertThat(savedEntity.getId()).isNotNull();
		assertThat(savedEntity.getEmail()).isEqualTo("TestMail123@hotmail.com");
		assertThat(savedEntity.getPhoneNumber()).isEqualTo("0031234567");
		assertThat(savedEntity.getMunicipalityId()).isEqualTo("2281");
		assertThat(savedEntity.getStatus()).isEqualTo("INACTIVE");
		assertThat(parsedEntity).isPresent();
	}

	@Test
	void updateUser() {
		final var userEntity = UserEntity.create().withId(UUID.randomUUID().toString()).withEmail(MAIL_ADRESS_1).withPhoneNumber(PHONE_NUMBER_1).withMunicipalityId(MUNICIPALITY_ID_1).withStatus(STATUS_1);

		final var savedEntity = userRepository.save(userEntity);
		assertThat(savedEntity.getEmail()).isEqualTo(MAIL_ADRESS_1);
		assertThat(savedEntity.getPhoneNumber()).isEqualTo(PHONE_NUMBER_1);
		assertThat(savedEntity.getMunicipalityId()).isEqualTo(MUNICIPALITY_ID_1);
		assertThat(savedEntity.getStatus()).isSameAs(STATUS_1);
		assertThat(savedEntity.getPhoneNumber()).isNotEqualTo(PHONE_NUMBER_2);
		assertThat(savedEntity.getMunicipalityId()).isNotEqualTo(MUNICIPALITY_ID_2);
		assertThat(savedEntity.getStatus()).isNotSameAs(STATUS_2);

		savedEntity.setPhoneNumber(PHONE_NUMBER_2);
		savedEntity.setMunicipalityId(MUNICIPALITY_ID_2);
		savedEntity.setStatus(STATUS_2);

		final var updatedEntity = userRepository.save(savedEntity);
		assertThat(updatedEntity.getEmail()).isEqualTo(MAIL_ADRESS_1);
		assertThat(updatedEntity.getPhoneNumber()).isEqualTo(PHONE_NUMBER_2);
		assertThat(updatedEntity.getMunicipalityId()).isEqualTo(MUNICIPALITY_ID_2);
		assertThat(updatedEntity.getStatus()).isSameAs(STATUS_2);
		assertThat(updatedEntity.getPhoneNumber()).isNotEqualTo(PHONE_NUMBER_1);
		assertThat(updatedEntity.getMunicipalityId()).isNotEqualTo(MUNICIPALITY_ID_1);
		assertThat(updatedEntity.getStatus()).isNotSameAs(STATUS_1);
	}

	@Test
	void getUserByMail() {
		final var testUser = userRepository.findById(MAIL_ADRESS_1);

		assertThat(testUser).isPresent().hasValueSatisfying(user -> {
			assertThat(user.getEmail()).isEqualTo(MAIL_ADRESS_1);
			assertThat(user.getPhoneNumber()).isEqualTo(PHONE_NUMBER_1);
			assertThat(user.getMunicipalityId()).isEqualTo(MUNICIPALITY_ID_1);
			assertThat(user.getStatus()).isEqualTo(STATUS_1);
		});
	}

	@Test
	void deleteUser() {
		assertThat(userRepository.findById(MAIL_ADRESS_2)).isPresent();
		userRepository.deleteById(MAIL_ADRESS_2);
		assertThat(userRepository.findById(MAIL_ADRESS_2)).isNotPresent();
	}
}
