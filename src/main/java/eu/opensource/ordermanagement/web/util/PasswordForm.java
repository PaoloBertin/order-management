package eu.opensource.ordermanagement.web.util;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class PasswordForm {

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Password is required")
    private String password2;
}
