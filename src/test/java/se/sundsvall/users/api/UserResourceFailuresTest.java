package se.sundsvall.users.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.violations.ConstraintViolationProblem;
import org.zalando.problem.violations.Violation;
import se.sundsvall.users.Application;
import se.sundsvall.users.api.model.UserRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;
import static org.zalando.problem.Status.BAD_REQUEST;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
class UserResourceFailuresTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void saveUserWithBadRequest() {
		// Arrange
		final var userRequest = UserRequest.create()
			.withEmail("notamailtestcom")
			.withPhoneNumber("number0000000000")
			.withMunicipalityId("id2222")
			.withStatus("status");
		// Act
		final var response = webTestClient.post()
			.uri("/api/users")
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
		Assertions.assertThat(response.getViolations())
			.extracting(Violation::getField, Violation::getMessage)
			.containsExactlyInAnyOrder(
				tuple("email", "must be a valid Email-adress"),
				tuple("status", "must be ACTIVE, INACTIVE or SUSPENDED"),
				tuple("phoneNumber", "must be a valid mobile number"),
				tuple("municipalityId", "must be a valid Municipality-ID"));
		// TODO verify response violation constraints

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
		assertThat(response.getViolations())
			.extracting(Violation::getField, Violation::getMessage)
			.containsExactlyInAnyOrder(
				tuple("getUserByEmail.email", "must be a well-formed email address"));
		// TODO verify response violation constraints

	}

	@Test
	void updateUserWithBadRequest() {
		// Arrange
		final String email = "test@testmail.com";
		final var userRequest = UserRequest.create()
			.withEmail(email)
			.withPhoneNumber("numberplate")
			.withMunicipalityId("municipalityId")
			.withStatus("status");

		// act
		final var response = webTestClient.put().uri("/api/users/{email}", email)
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
		assertThat(response.getViolations())
			.extracting(Violation::getField, Violation::getMessage)
			.containsExactlyInAnyOrder(
				tuple("status", "must be ACTIVE, INACTIVE or SUSPENDED"),
				tuple("phoneNumber", "must be a valid mobile number"),
				tuple("municipalityId", "must be a valid Municipality-ID"));
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
		assertThat(response.getViolations())
			.extracting(Violation::getField, Violation::getMessage)
			.containsExactlyInAnyOrder(
				tuple("deleteByEmail.email", "must be a well-formed email address"));
		// TODO verify response violation constraints
	}
}
