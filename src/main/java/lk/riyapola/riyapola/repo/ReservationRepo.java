package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation,Integer> {
//    List<Reservation> findReservationByCustomerId(Integer customerId);
}
