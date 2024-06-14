package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.ReservationDTO;
import lk.riyapola.riyapola.entity.Reservation;
import lk.riyapola.riyapola.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dulanjali
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.service
 * Date : 6/13/2024
 * Time :12:14 AM
 */
@Service
public class ReservationService {

    private  final ReservationRepo reservationRepo;
@Autowired
    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }
    public Reservation saveReservation(ReservationDTO reservationDTO){
        Reservation save=reservationRepo.save(new Reservation(
                reservationDTO.getStartDate(),
                 reservationDTO.getStartTime(),
                 reservationDTO.getEndDate(),
                 reservationDTO.getEndTime(),
                reservationDTO.getPicUpLocation(),
                reservationDTO.getStatus(),
                reservationDTO.getCarId(),
                reservationDTO.getCustomer_id()

        ));
    return  save;
    }
    public List<Reservation> getAllReservation(){
    return  null;
    }
}
