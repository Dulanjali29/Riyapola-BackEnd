package lk.riyapola.riyapola.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDate;

/**
 * Created by dulanjali
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.entity
 * Date : 6/13/2024
 * Time :12:14 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;
    private LocalDate startDate;
    private Time startTime;
    private LocalDate endDate;
    private Time endTime;
    private String picUpLocation;
    private Integer carId;
    private Integer customer_id;
    private  String status;

    public Reservation(LocalDate startDate, Time startTime, LocalDate endDate, Time endTime, String picUpLocation, String status, Integer carId, Integer customerId) {
    }
}
