package se.sundsvall.users.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.violations.ConstraintViolationProblem;

import org.zalando.problem.violations.Violation;
import se.sundsvall.users.Application;
import se.sundsvall.users.api.model.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;
import static org.zalando.problem.Status.BAD_REQUEST;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public class UserResourceFailuresTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void saveUserWithBadRequest() {
		// Arrange
		final String email = "testom";
		final var userRequest = User.create()
			.withEmail(email)
			.withPhoneNumber("ej3di352")
			.withMunicipalityId("23ve45")
			.withStatus("oklart");

		// Act
		final var response = webTestClient.post()
			.uri("/api/users/{email}", email)
			.contentType(APPLICATION_JSON)
			.bodyValue(userRequest)
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON)
			.expectBody(ConstraintViolationProblem.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getTitle()).isEqualTo("Constraint Violation");
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);

//        assertThat(response.getViolations())
//                .extracting(Violation::getField, Violation::getMessage)
//                .containsExactlyInAnyOrder(
//                        tuple("email", "must be a valid Email-adress"),
//                        tuple("status", "status must be Active, Suspended or Inactive"),
//                        tuple("phoneNumber", "must be a Phone-number"),
//                        tuple("municipalityId", "must be a Municipality-ID"));
		// TODO verify response violation constraints aka make this work

	}

	@Test
	void getUserWithInvalidId() {
		// Arrange
		final String email = "kallekula";

		// Act
		final var response = webTestClient.get().uri("/api/users/{email}", email)
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON)
			.expectBody(ConstraintViolationProblem.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getTitle()).isEqualTo("Constraint Violation");
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);
		// TODO verify response violation constraints

	}

	@Test
	void updateUserWithBadRequest() {
		// Arrange
		final String epost = "kalle.kula@sundsvall.se";
		final var userRequest = User.create()
			.withEmail(epost)
			.withPhoneNumber("ej3di352")
			.withMunicipalityId("23ve45")
			.withStatus("oklart");

		// act
		final var response = webTestClient.put().uri("/api/users/{email}", epost)
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON)
			.expectBody(ConstraintViolationProblem.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getTitle()).isEqualTo("Constraint Violation");
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);
	}

	@Test
	void deleteUserWithInvalidId() {
		// Arrange
		final String email = "kallekula";

		// Act
		final var response = webTestClient.delete()
			.uri("/api/users/{email}", email)
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON)
			.expectBody(ConstraintViolationProblem.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getTitle()).isEqualTo("Constraint Violation");
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);
		// TODO verify response violation constraints
	}
}
