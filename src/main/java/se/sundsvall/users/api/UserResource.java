package se.sundsvall.users.api;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sundsvall.users.api.model.CreateUserRequest;
import se.sundsvall.users.api.model.CreateUserResponse;
import se.sundsvall.users.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    public static UserResource create(){
        return new UserResource();
    }

    @PostMapping
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        response.setEmail("kalle.kula@sundsvall.se");
        response.setPhoneNumber(request.getPhoneNumber());

        return response;
    }
}
