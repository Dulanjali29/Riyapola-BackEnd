package lk.riyapola.riyapola.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarDTO {
    private Integer car_id;
    private String brand;
    private  String  model;
    private int noOfPassengers;
    private String fuelType;
    private  double dailyRentalPrice;
    private String  status;
}
