package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Customer findCustomerByFirstName(String name);
   
   Customer findCustomerByUserName(String userName);


    @Query(nativeQuery = true,value = "SELECT password FROM customer WHERE user_name= :userName")
    String passwordByUserName(String userName);
}
