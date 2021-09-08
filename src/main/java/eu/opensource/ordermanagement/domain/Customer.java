package eu.opensource.ordermanagement.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@RequiredArgsConstructor
@Table(name = "customers")
@Entity
public class Customer implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    @Transient
    private String nickname;

    @OneToOne
    @JoinColumn(name = "header_addres_id", foreignKey = @ForeignKey(name = "customers_fk_01"))
    private Address header_addres;

    @OneToOne
    @JoinColumn(name = "delivery_addres_id", foreignKey = @ForeignKey(name = "customers_fk_02"))
    private Address delivery_addres;

    @OneToOne
    @JoinColumn(name = "contact_id", foreignKey = @ForeignKey(name = "customers_fk_03"))
    private Contact contact;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customer_role",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public String getNickname() {

        return firstname + " " + lastname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    @Override
    public String toString() {

        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
