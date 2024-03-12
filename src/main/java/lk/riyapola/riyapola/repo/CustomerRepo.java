package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
}
