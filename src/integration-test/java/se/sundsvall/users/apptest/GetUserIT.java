package se.sundsvall.users.apptest;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.users.Application;
import se.sundsvall.users.integration.db.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@WireMockAppTestSuite(files = "classpath:/GetUserIT/", classes = Application.class)
@Sql(scripts = {
        "/db/script/truncate.sql",
        "/db/script/GetUserTest.sql"
})
class GetUserIT extends AbstractAppTest {
    private static final String RESPONSE = "response.json";
    private static final String Expected = "citizen-mapping.json";
    @Autowired
    UserRepository userRepository;

    @Test
    void test07_getUserByEmail() {

        final String email = "testmail1@sundsvall.se";

        assertThat(userRepository.findByEmail(email)).isPresent();

        setupCall()
                .withServicePath("/api/users/email/".concat(email))
                .withHttpMethod(HttpMethod.GET)
                .withExpectedResponseStatus(HttpStatus.OK)
                .withExpectedResponse(RESPONSE)
                .sendRequestAndVerifyResponse();


    }

    @Test
    void test08_getUserByPartyId() {

        final String partyId = "7225dc69-28d1-4064-a1a8-5c1de5da0e62";

        assertThat(userRepository.findByPartyId(partyId)).isPresent();

        setupCall()
                .withServicePath("/api/users/partyIds/".concat(partyId))
                .withHttpMethod(HttpMethod.GET)
                .withExpectedResponseStatus(HttpStatus.OK)
                .withExpectedResponse(RESPONSE)
                .sendRequestAndVerifyResponse();


    }
    @Test
    void test09_getUserByPersonNumber() {

        final String personNumber = "198001011234";
        final String partyId = "7225dc69-28d1-4064-a1a8-5c1de5da0e62";
        final String municipalityId = "2281";


        assertThat(userRepository.findByPartyId(partyId));

        setupCall()
                .withServicePath("/api/users/personalNumbers/198001011234?municipalityId=2281")
                .withHttpMethod(HttpMethod.GET)
                .withExpectedResponseStatus(HttpStatus.OK)
                .withExpectedResponse(RESPONSE)
                .sendRequestAndVerifyResponse();

    }
}