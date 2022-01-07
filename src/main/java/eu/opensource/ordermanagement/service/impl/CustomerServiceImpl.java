package eu.opensource.ordermanagement.service.impl;

import eu.opensource.ordermanagement.domain.Customer;
import eu.opensource.ordermanagement.domain.Role;
import eu.opensource.ordermanagement.repository.CustomerRepository;
import eu.opensource.ordermanagement.service.CustomerService;
import eu.opensource.ordermanagement.service.RoleService;
import eu.opensource.ordermanagement.web.util.SignupForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service("customerService")
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private final CustomerRepository customerRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {

        return customerRepository.findById(customerId);
    }

    @Override
    public Customer getCustomerByUsername(String email) {

        return customerRepository.findByUsername(email);
    }

    @Override
    public Customer getCustomerByUsernameAndPassword(String email, String password) {

        return customerRepository.findByUsernameAndPassword(email, password);
    }

    @Override
    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Customer registrationCustomer(SignupForm signupForm) {

//        String encodedPassword = passwordEncoder.encode(signupForm.getPassword1());

        // crea nuovo utente
        Customer customer = new Customer();
        customer.setFirstname(signupForm.getFirstname());
        customer.setLastname(signupForm.getLastname());
        customer.setUsername(signupForm.getUsername());
//        customer.setPassword(signupForm.getPassword1());
        customer.setPassword(passwordEncoder.encode(signupForm.getPassword1()));

        // crea ruolo
        Role role = roleService.getRoleByName("ROLE_USER");
        // aggiunge ruolo a customer
        customer.getRoles()
                .add(role);
        // aggiunge customer a role
        role.getCustomers()
            .add(customer);

        log.info("Customer: {}", customer);

        // rende persistenti firstname, lastname, email e password del nuovo utente
        customer = customerRepository.save(customer);

        return customer;
    }

    @Override
    public Customer getCurrentCustomer() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }

        Customer customer = (Customer) authentication.getPrincipal();
        String email = customer.getUsername();
        if (email == null) {
            return null;
        }
        Customer result = getCustomerByUsername(email);
        if (result == null) {
            throw new IllegalStateException("Could not find customer with email " + email);
        }

        log.info("CalendarUser: {}", result);
        return result;
    }

    @Override
    public void setCurrentCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        UserDetails userDetails = loadUserByUsername(customer.getUsername()); // ritorna un oggetto CustomerUserDetails

        String password = customer.getPassword();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                                                                                                     password,
                                                                                                     userDetails.getAuthorities());
        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUsername(username);

        if (customer != null) {
            return customer;
        }
        throw new UsernameNotFoundException("Customer '" + username + "' not found");
    }
}
