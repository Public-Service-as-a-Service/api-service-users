package se.sundsvall.users.apptest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.users.Application;
import se.sundsvall.users.integration.db.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Update user apptests.
 *
 * @see /src/test/resources/db/script/UpdateUserAppTest.sql for data setup.
 */
@WireMockAppTestSuite(files = "classpath:/UpdateUserIT/", classes = Application.class)
@Sql(scripts = {
        "/db/script/truncate.sql",
        "/db/script/UpdateUserAppTest.sql"
})
class UpdateUserIT extends AbstractAppTest {
    private static final String REQUEST = "request.json";
    private static final String RESPONSE = "response.json";

    @Autowired
    private UserRepository userRepository;

    @Test
    void test01_UpdateUserWithEmail() {

        final String partyId = "7225dc69-28d1-4064-a1a8-5c1de5da0e62";
        final String email = "testmail1@sundsvall.se";

        assertThat(userRepository.findByEmail(email)).isPresent();

        setupCall()
                .withServicePath("/api/users/email/".concat(email))
                .withHttpMethod(HttpMethod.PUT)
                .withRequest(REQUEST)
                .withExpectedResponseStatus(HttpStatus.CREATED)
                .withExpectedResponse(RESPONSE)
                .sendRequestAndVerifyResponse();

    }
}
