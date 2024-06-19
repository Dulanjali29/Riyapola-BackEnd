package lk.riyapola.riyapola.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

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
}
