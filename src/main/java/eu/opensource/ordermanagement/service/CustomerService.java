package eu.opensource.ordermanagement.service;

import eu.opensource.ordermanagement.domain.Customer;
import eu.opensource.ordermanagement.web.util.SignupForm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getCustomerById(Long id);

    Customer getCustomerByUsername(String email);

    Customer getCustomerByUsernameAndPassword(String email, String password);

    UserDetails loadUserByUsername(String email);

    Customer saveCustomer(final Customer customer);

    Customer registrationCustomer(SignupForm signupForm);

    Customer getCurrentCustomer();

    void setCurrentCustomer(Customer customer);
}