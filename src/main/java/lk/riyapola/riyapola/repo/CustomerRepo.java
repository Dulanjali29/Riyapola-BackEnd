package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {


  List<Customer> findCustomerByUserName(String userName);


    @Query(nativeQuery = true,value = "SELECT password FROM customer WHERE user_name= :userName")
    String passwordByUserName(String userName);
    @Query(nativeQuery = true, value = "SELECT * FROM customer WHERE customer_id = :customerId")
    Customer getCustomerByCustomerId(Integer customerId);
//    Customer getCustomerByDateTime(String dateTime);
}

