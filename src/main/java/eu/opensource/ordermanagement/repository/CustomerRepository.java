package eu.opensource.ordermanagement.repository;

import eu.opensource.ordermanagement.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUsername(String email);

    Customer findByUsernameAndPassword(String email, String password);
}
