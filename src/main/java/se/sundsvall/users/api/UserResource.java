package se.sundsvall.users.api;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.model.UserEntity;
import se.sundsvall.users.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    //LÄGG TILL: CREATE + finns eposten ruternera felmeddelande
    @PostMapping("POST/users")
    public void saveUser(@RequestBody String email, String phoneNumber, String municipalityId, String status){
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setPhoneNumber(phoneNumber);
        userRequest.setMunicipalityId(municipalityId);
        userRequest.setStatus(status);
        userService.createUser(userRequest);
    }


    @GetMapping("GET/users/{email}")
    public UserResponse getUserById(@RequestBody String id) {
        UserRequest user = userService.getUserByID(id);
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setMunicipalityId((user.getMunicipalityId()));
        return userResponse;
        //LÄGG TILL: saknas användare returnera felmeddelande
    }

    //LÄGG TILL UPDATE + saknas användare returnera felmeddelande

    //LÄGG TILL: DELETE + om Eposten inte finns returnera felmeddelande

}
