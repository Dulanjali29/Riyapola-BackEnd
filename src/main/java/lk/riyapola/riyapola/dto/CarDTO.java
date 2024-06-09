package lk.riyapola.riyapola.dto;

import lk.riyapola.riyapola.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarDTO {
    private Integer carId;
    private String brand;
    private String model;
    private String noOfPassengers;
    private String fuelType;
    private String transmissionMode;
    private double dailyRentalPrice;
    private String status;
    private List<CarImageGetDto> images;




}
