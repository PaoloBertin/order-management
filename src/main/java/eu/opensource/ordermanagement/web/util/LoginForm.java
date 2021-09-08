package eu.opensource.ordermanagement.web.util;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class LoginForm {

    @Email(message = "Please provide a valid email address")
    @NotEmpty(message = "Email is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;
}
