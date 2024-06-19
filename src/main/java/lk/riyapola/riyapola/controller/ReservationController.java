package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.ReservationDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.entity.Reservation;
import lk.riyapola.riyapola.service.ReservationService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dulanjali
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.controller
 * Date : 6/13/2024
 * Time :12:14 AM
 */
@CrossOrigin
@RestController
@RequestMapping("/riyapola/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final JWTTokenGenerator jwtTokenGenerator;
    @Autowired
    public ReservationController(ReservationService reservationService, JWTTokenGenerator jwtTokenGenerator) {
        this.reservationService = reservationService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }
    @PostMapping("/addReservation")
    public ResponseEntity<Object> saveReservation(@RequestHeader(name = "Authorization") String authorizationHeader, @RequestBody ReservationDTO reservationDTO){
        if(jwtTokenGenerator.validateJwtToken(authorizationHeader)){

            Reservation savereservation = reservationService.saveReservation(reservationDTO);
            return  new ResponseEntity<>(savereservation, HttpStatus.CREATED);

        }else {
            return  new ResponseEntity<>("Invalid Token",HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/getAllReservation")
    public ResponseEntity<Object> getAllReservation(){
        return  null;
    }


}
