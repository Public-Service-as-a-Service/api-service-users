package se.sundsvall.users.api.model;

import org.junit.jupiter.api.Test;
import se.sundsvall.users.integration.model.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRequestTest {
    @Test
    void testBuildMethod() {
        final var email = "email";
        final var phoneNumber = "phoneNumber";
        final var municipalityId = "municipalityId";
        final var status = "status";

        final var userEntity = UserEntity.create()
                .withEmail(email)
                .withPhoneNumber(phoneNumber)
                .withMunicipalityId(municipalityId)
                .withStatus(status);

        assertThat(userEntity.getEmail()).isEqualTo(email);
        assertThat(userEntity.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(userEntity.getMunicipalityId()).isEqualTo(municipalityId);
        assertThat(userEntity.getStatus()).isEqualTo(status);
    }
}
