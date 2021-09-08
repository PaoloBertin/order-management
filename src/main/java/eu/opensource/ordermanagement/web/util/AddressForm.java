package eu.opensource.ordermanagement.web.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressForm {

    private Long Id;  // customerId

    private String firstname;

    private String lastname;

    private Long addressId;

    private String houseNumber;

    private String street;

    private String city;

    private String country;

    private String state;
}
