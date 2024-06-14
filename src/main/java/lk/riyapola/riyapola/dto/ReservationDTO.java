package lk.riyapola.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

/**
 * Created by dulanjali
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.dto
 * Date : 6/13/2024
 * Time :12:15 AM
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReservationDTO {
    private Integer reservationId;
    private LocalDate startDate;
    private Time startTime;
    private LocalDate endDate;
    private Time endTime;
    private String picUpLocation;
    private Integer carId;
    private Integer customer_id;
    private  String status;
}
