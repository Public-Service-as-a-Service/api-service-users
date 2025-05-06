package se.sundsvall.users.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.users.Application;
import se.sundsvall.users.api.model.User;
import se.sundsvall.users.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")

public class UserResourceTest {

	@MockBean
	private UserService userServiceMock;

	@Autowired
	private WebTestClient webTestClient;

	// Se om det ska vara en void metod eller om vi ska retunera ett respone
	@Test
	void saveUser() {
		final String email = "test@test.com";
		final var userRequest = User.create()
			.withMunicipalityId("2281")
			.withPhoneNumber("0703423521")
			.withStatus("active");
		final var userResponse = User.create()
			.withEmail(email)
			.withMunicipalityId("2281")
			.withPhoneNumber("0703423521")
			.withStatus("active");

		when(userServiceMock.createUser(any(User.class), eq(email))).thenReturn(userResponse);
		var response = webTestClient.post()
			.uri("/api/users/{email}", email)
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(userRequest)
			.exchange()
			.expectStatus().isOk()
			.expectBody(User.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isEqualTo(null); // ska resultatet returnera eller fungerar det med void?
		verify(userServiceMock).createUser(userRequest, email);

	}

	@Test
	void getUserByEmail() { // Detta funkar RÖR EJ!!!
		final var email = "kalle.kula@sundsvall.se";
		final var userResponse = User.create()
			.withEmail(email)
			.withMunicipalityId("2281")
			.withPhoneNumber("0703423521")
			.withStatus("active");

		when(userServiceMock.getUserByEmail(email)).thenReturn(userResponse);

		final var response = webTestClient.get()
			.uri("/api/users/{email}", email)
			.exchange()
			.expectStatus().isOk()
			.expectBody(User.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isEqualTo(userResponse);
		verify(userServiceMock).getUserByEmail(email);
	}

	@Test
	void updateUser() {
		final var email = "test@test.com";
		final var userRequest = User.create()
			.withMunicipalityId("2281")
			.withPhoneNumber("0703423521")
			.withStatus("active");
		final var userResponse = User.create()
			.withEmail(email)
			.withMunicipalityId("2281")
			.withPhoneNumber("0703423521")
			.withStatus("active");

		when(userServiceMock.updateUser(any(User.class), eq(email))).thenReturn(userResponse);

		final var response = webTestClient.put()
			.uri("/api/users/{email}", email)
			.bodyValue(userRequest)
			.exchange()
			.expectStatus().isOk()
			.expectBody(User.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isEqualTo(userResponse);
		verify(userServiceMock).updateUser(refEq(userRequest), eq(email));

	}

	@Test
	void deleteUser() {
		final var email = "test@test.com";

		when(userServiceMock.deleteUser(email)).thenReturn(null);
		final var response = webTestClient.delete()
			.uri("/api/users/{email}", email)
			.exchange()
			.expectStatus().isOk()
			.expectBody(User.class)
			.returnResult()
			.getResponseBody();

		verify(userServiceMock).deleteUser(email);

	}

}
