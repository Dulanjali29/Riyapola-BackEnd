package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation,Integer> {
}
