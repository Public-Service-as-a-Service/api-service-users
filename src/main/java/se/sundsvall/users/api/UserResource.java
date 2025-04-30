package se.sundsvall.users.api;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;
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
	// Se om det ska vara en void metod eller om vi ska retunera ett respone
	@PostMapping("users")
	public void saveUser(@RequestBody @Valid UserRequest userRequest) {
		userService.createUser(userRequest);
	}

	@GetMapping("users/{email}")
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<UserResponse> getUserByEmail(@Valid @Email @PathVariable String email) {
		var user = userService.getUserByEmail(email);
		return user != null ? ok(user) : ResponseEntity.noContent().build();
	}

	@PutMapping("users/{email}")
	@Validated
	public ResponseEntity<UserResponse> updateUser(@Email @PathVariable String email, @RequestBody @Valid UpdateUserRequest userRequest) {
		var user = userService.updateUser(userRequest, email);
		return user != null ? ok(user) : ResponseEntity.noContent().build();
	}

	@DeleteMapping("users/{email}")
	public void deleteByEmail(@Valid @Email @PathVariable String email) {
		userService.deleteUser(email);
	}
}
