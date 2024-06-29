package lk.riyapola.riyapola.controller;

import jakarta.mail.MessagingException;
import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.dto.MailDetailsDTO;
import lk.riyapola.riyapola.dto.ReservationDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.entity.Reservation;
import lk.riyapola.riyapola.service.ReservationService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    private final JavaMailSender javaMailSender;
    @Autowired
    public ReservationController(ReservationService reservationService, JWTTokenGenerator jwtTokenGenerator, JavaMailSender javaMailSender) {
        this.reservationService = reservationService;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.javaMailSender = javaMailSender;
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
        List<Reservation> getDto=reservationService.getAllReservation();

        return  new ResponseEntity<>(getDto,HttpStatus.OK);
    }

    @PostMapping("/send/mail")
    public  String sendEmail(@RequestBody MailDetailsDTO mailDetailsDTO)throws MessagingException, IOException {
        try{
            SimpleMailMessage simpleMessage=new SimpleMailMessage();
            simpleMessage.setSubject(mailDetailsDTO.getSubject());
            simpleMessage.setTo(mailDetailsDTO.getToMail());
            simpleMessage.setFrom("mail0slkiller@gmail.com");
            simpleMessage.setText(mailDetailsDTO.getMessage());

            javaMailSender.send(simpleMessage);
            System.out.println(simpleMessage);
            return "Email Send Success";
        }catch (Exception e){
        return  e.getMessage();
        }
    }
    @PutMapping("/reservationUpdate/{reservationId}")
    public ResponseEntity<Object> updateReservation(@PathVariable Integer reservationId, @RequestBody ReservationDTO reservationDTO, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
           Reservation reservation = reservationService.updateReservation(reservationId,reservationDTO);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token ", HttpStatus.FORBIDDEN);
        }

    }
//    @GetMapping("/searchReservation/{customerId}")
//    public ResponseEntity<Object> seacrchReservation(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer customerId){
//        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
//            List<Reservation> reservation = reservationService.searchReservation(customerId);
//            return new ResponseEntity<>(reservation, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid token ", HttpStatus.FORBIDDEN);
//        }
//    }
}
