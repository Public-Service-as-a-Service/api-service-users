package se.sundsvall.users.api.model;

import org.junit.jupiter.api.Test;
import se.sundsvall.users.integration.model.UserEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateUserRequestTest {
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
}
