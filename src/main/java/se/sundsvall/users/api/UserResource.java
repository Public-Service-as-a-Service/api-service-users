package se.sundsvall.users.api;

import com.nimbusds.openid.connect.sdk.assurance.evidences.PersonalNumber;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;
import se.sundsvall.dept44.common.validators.annotation.ValidPersonalNumber;
import se.sundsvall.users.api.model.UpdateUserRequest;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.service.UserService;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
@Validated
@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {
	Problem.class, ConstraintViolationProblem.class
})))
@ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
@ApiResponse(responseCode = "503", description = "Server Error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
public class UserResource {
	private final UserService userService;

	public UserResource(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("users")
	@ApiResponse(responseCode = "201", description = "Successful operation", useReturnTypeSchema = true)
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid UserRequest userRequest) {
		final var user = userService.createUserWithCitizenDB(userRequest);
		return ResponseEntity.created(UriComponentsBuilder.fromPath("/api/user/").buildAndExpand(userRequest).toUri())
			.body(user);
	}

	@GetMapping("users/email/{email}")
	public ResponseEntity<UserResponse> getUserByEmail(@Valid @Email @PathVariable String email) {
		var user = userService.getUserByEmail(email);
		return user != null ? ok(user) : ResponseEntity.noContent().build();
	}

	@GetMapping("users/PN/{personalNumber}")
	public ResponseEntity<UserResponse> getUserByPersonalNumber(@Valid @ValidPersonalNumber @PathVariable String personalNumber) {
		var user = userService.getUserByPersonalNumber(personalNumber);
		return user != null ? ok(user) : ResponseEntity.noContent().build();
	}

	@PutMapping("users/email/{email}")
	@ApiResponse(responseCode = "201", description = "Successful operation", useReturnTypeSchema = true)
	@Validated
	public ResponseEntity<UserResponse> updateUser(@Valid @Email @PathVariable String email, @RequestBody @Valid UpdateUserRequest userRequest) {
		var user = userService.updateUser(userRequest, email);
		return ResponseEntity.created(UriComponentsBuilder.fromPath("/api/user/").buildAndExpand(userRequest).toUri())
			.body(user);
	}

	@PutMapping("users/PN/{personalNumber}")
	@ApiResponse(responseCode = "201", description = "Successful operation", useReturnTypeSchema = true)
	@Validated
	public ResponseEntity<UserResponse> updateUserByPersonalNumber(@Valid @ValidPersonalNumber @PathVariable String personalNumber, @RequestBody @Valid UpdateUserRequest userRequest) {
		var user = userService.updateUser(userRequest, personalNumber);
		return ResponseEntity.created(UriComponentsBuilder.fromPath("/api/user/").buildAndExpand(userRequest).toUri())
			.body(user);
	}

	@DeleteMapping("users/email/{email}")
	@ApiResponse(responseCode = "204", description = "Successful operation", useReturnTypeSchema = true)
	public ResponseEntity<Void> deleteByEmail(@Valid @Email @PathVariable String email) {
		userService.deleteUserByEmail(email);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("users/PN/{personalNumber}")
	@ApiResponse(responseCode = "204", description = "Successful operation", useReturnTypeSchema = true)
	public ResponseEntity<Void> deleteByPersonalNumber(@Valid @ValidPersonalNumber @PathVariable String personalNumber) {
		userService.deleteUserByPN(personalNumber);
		return ResponseEntity.noContent().build();
	}
}
