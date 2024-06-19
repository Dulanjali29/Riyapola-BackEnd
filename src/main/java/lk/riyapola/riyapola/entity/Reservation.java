package lk.riyapola.riyapola.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;
    private String picUpLocation;
    private Integer carId;
    private Integer customer_id;
    private  String status;

    public Reservation(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String picUpLocation, Integer carId, Integer customer_id, String status) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.picUpLocation = picUpLocation;
        this.carId = carId;
        this.customer_id = customer_id;
        this.status = status;
    }

    public Reservation(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String picUpLocation, String status, Integer carId, Integer customerId) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.picUpLocation = picUpLocation;
        this.carId = carId;
        this.customer_id = customerId;
        this.status = status;
    }
}
