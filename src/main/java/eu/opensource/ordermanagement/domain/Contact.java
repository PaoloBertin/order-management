package eu.opensource.ordermanagement.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "contacts")
@Entity
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String cellular;

    @Column(name = "landline_phone")
    private String landlinePhone;

}
