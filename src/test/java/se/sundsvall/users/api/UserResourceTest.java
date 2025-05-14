package se.sundsvall.users.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.users.Application;
import se.sundsvall.users.api.model.UpdateUserRequest;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")

class UserResourceTest {

	@MockBean
	private UserService userServiceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void saveUser() {

		final var userRequest = UserRequest.create()
			.withEmail("test@test.com")
			.withMunicipalityId("2281")
			.withPhoneNumber("0703423521")
			.withStatus("ACTIVE");
		final var userResponse = UserResponse.create()
			.withEmail("test@test.com")
			.withMunicipalityId("2281")
			.withPhoneNumber("0703423521")
			.withStatus("ACTIVE");

		when(userServiceMock.createUser(any(UserRequest.class))).thenReturn(userResponse);
		var response = webTestClient.post()
			.uri("/api/users")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(userRequest)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(UserResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isEqualTo(userResponse);
		verify(userServiceMock).createUser(userRequest);
		verifyNoMoreInteractions(userServiceMock);

	}

	@Test
	void getUserByEmail() {
		final var email = "kalle.kula@sundsvall.se";
		final var userResponse = UserResponse.create()
			.withEmail(email)
			.withMunicipalityId("2281")
			.withPhoneNumber("0703423521")
			.withStatus("ACTIVE");

		when(userServiceMock.getUserByEmail(email)).thenReturn(userResponse);

		final var response = webTestClient.get()
			.uri("/api/users/{email}", email)
			.exchange()
			.expectStatus().isOk()
			.expectBody(UserResponse.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isEqualTo(userResponse);
		verify(userServiceMock).getUserByEmail(email);
	}

	@Test
	void updateUser() {
		final var email = "test@test.com";
		final var userRequest = UpdateUserRequest.create()
			.withMunicipalityId("2281")
			.withPhoneNumber("0703423521")
			.withStatus("ACTIVE");
		final var userResponse = UserResponse.create()
			.withEmail(email)
			.withMunicipalityId("2281")
			.withPhoneNumber("0703423521")
			.withStatus("ACTIVE");

		when(userServiceMock.updateUser(any(UpdateUserRequest.class), eq(email))).thenReturn(userResponse);

		final var response = webTestClient.put()
			.uri("/api/users/{email}", email)
			.bodyValue(userRequest)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(UserResponse.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isEqualTo(userResponse);
		verify(userServiceMock).updateUser(userRequest, email);

	}

	@Test
	void deleteUser() {
		final var email = "test@test.com";

		doNothing().when(userServiceMock).deleteUser(email);
		final var response = webTestClient.delete()
			.uri("/api/users/{email}", email)
			.exchange()
			.expectStatus().isNoContent()
			.expectBody(UserResponse.class)
			.returnResult()
			.getResponseBody();

		verify(userServiceMock).deleteUser(email);

	}

}
