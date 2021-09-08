package eu.opensource.ordermanagement.web.util;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class CustomerForm {

    private Long id;

    @NotEmpty(message = "First Name is required")
    private String firstname;

    @NotEmpty(message = "Last Name is required")
    private String lastname;

    @Email(message = "Please provide a valid email address")
    @NotEmpty(message = "Email is required")
    private String username;
}
