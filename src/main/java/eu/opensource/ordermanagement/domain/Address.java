package eu.opensource.ordermanagement.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "addresses")
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "zip_code")
    private String zipCode;

    private String country;

    private String state;

    private String firstname;

    private String lastname;
}
