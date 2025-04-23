package se.sundsvall.users.api;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.sundsvall.users.api.model.UserRequest;
import se.sundsvall.users.api.model.UserResponse;
import se.sundsvall.users.integration.model.UserEntity;
import se.sundsvall.users.service.UserService;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    //LÄGG TILL: CREATE + finns eposten ruternera felmeddelande
    @PostMapping("POST/users")
    @Validated
    public void saveUser(String email, String phoneNumber, String municipalityId, String status){
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setPhoneNumber(phoneNumber);
        userRequest.setMunicipalityId(municipalityId);
        userRequest.setStatus(status);
        userService.createUser(userRequest);
    }


    @GetMapping("GET/users/{email}") //LÄGG TILL: saknas användare returnera felmeddelande
    @ExceptionHandler(UsernameNotFoundException.class)
    public UserResponse getUserById(@RequestBody @Valid String id) {
        UserRequest user = userService.getUserByID(id);
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setMunicipalityId((user.getMunicipalityId()));
        return userResponse;
    }

    @PutMapping("PUT/users/{email}")//LÄGG TILL: saknas användare returnera felmeddelande
    @Validated
    public void updateUser(String email, String phoneNumber, String municipalityId, String status){
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setMunicipalityId(municipalityId);
        userRequest.setPhoneNumber(phoneNumber);
        userRequest.setStatus(status);
        userService.updateUser(userRequest, email);
    }

    @DeleteMapping("DELETE/users/{email}") //LÄGG TILL: om Eposten inte finns returnera felmeddelande
    public void deleteById(@Valid String id) {
        userService.deleteUser(id);
    }


}
