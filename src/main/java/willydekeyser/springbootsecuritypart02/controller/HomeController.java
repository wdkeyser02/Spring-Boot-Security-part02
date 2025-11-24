package willydekeyser.springbootsecuritypart02.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication) {
        String username = "Anonymous";
        if (authentication != null) {
            username = authentication.getName().toUpperCase();
        }
        return ("""
                <center>
                <h1>Spring Boot Tutorial</h1>
                <h2>Home Page!</h2>
                <p>Username: %s</p>
                <a href='/public'>Public</a><br/>
                <a href='/user'>Private for User</a><br/>
                <a href='/admin'>Private for Admin</a>
                </center>
                """).formatted(username);
    }

    @GetMapping("/public")
    public String public_page(Authentication authentication) {
        String username = "Anonymous";
        if (authentication != null) {
            username = authentication.getName().toUpperCase();
        }
        return ("""
                <center>
                <h1>Spring Boot Tutorial</h1>
                <h2>Public Page!</h2>
                <p>Username: %s</p>
                <a href='/'>Home</a>
                </center>
                """).formatted(username);
    }

    @GetMapping("/user")
    public String private_page_user(Principal principal) {
        return ("""
                <center>
                <h1>Spring Boot Tutorial</h1>
                <h2>Private Page for User!</h2>
                <p>Username: %s</p>
                <a href='/'>Home</a>
                </center>
                """).formatted(principal.getName().toUpperCase());
    }

    @GetMapping("/admin")
    public String private_page_admin(Authentication authentication) {
        return ("""
                <center>
                <h1>Spring Boot Tutorial</h1>
                <h2>Private Page for Admin!</h2>
                <p>Username: %s</p>
                <p>Roles: %s</p>
                <a href='/'>Home</a>
                </center>
                """).formatted(authentication.getName().toUpperCase(), authentication.getAuthorities());
    }
}
