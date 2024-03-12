package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Customer findCustomerByFirstName(String name);
    Customer findByUserNameAndPassword(String userName, String password);
}
