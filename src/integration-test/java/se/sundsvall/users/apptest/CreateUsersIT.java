package se.sundsvall.users.apptest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.users.Application;
import se.sundsvall.users.integration.db.UserRepository;
import se.sundsvall.users.integration.db.model.Enum.Status;


import static org.assertj.core.api.Assertions.assertThat;


@WireMockAppTestSuite(files = "classpath:/CreateUsersIT/", classes = Application.class)
@ActiveProfiles("default")
class CreateUsersIT extends AbstractAppTest {
    private static final String Request = "request.json";
    private static final String Response = "response.json";

    private static final String EMAIL = "test@sundsvall.se";
    private static final String PERSONALNUMBER = "198602300337";
    private static final String PHONE_NUMBER = "0701234567";
    private static final String MUNICIPALITY_ID = "1440";
    private static final Status STATUS = Status.INACTIVE;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test01_createUser() {

        assertThat(userRepository.findByEmail(EMAIL)).isEmpty();

        setupCall()
                .withServicePath("/api/users")
                .withHttpMethod(HttpMethod.POST)
                .withRequest(Request)
                .withExpectedResponseStatus(HttpStatus.CREATED)
                .sendRequestAndVerifyResponse();

        final var user = userRepository.findByEmail(EMAIL);
        assertThat(user).isPresent();
        assertThat(user.get().getStatus()).isNotNull();
        assertThat(user.get().getEmail()).isEqualTo(EMAIL);
        assertThat(user.get().getPhoneNumber()).isEqualTo(PHONE_NUMBER);
        assertThat(user.get().getMunicipalityId()).isEqualTo(MUNICIPALITY_ID);
        assertThat(user.get().getStatus()).isEqualTo(STATUS);

    }

}
