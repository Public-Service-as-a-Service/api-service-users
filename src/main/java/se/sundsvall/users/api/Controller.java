package se.sundsvall.users.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    //Test
    @GetMapping("/api")
    public String getSting() {
        return "Hello World";
    }
}
