package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car,Integer> {
}
