package lk.riyapola.riyapola.dto;

import jakarta.persistence.Entity;
import lk.riyapola.riyapola.entity.CarImg;
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
    private int carId;
    private String brand;
    private  String  model;
    private String noOfPassengers;
    private String fuelType;
    private String transmissionMode;
    private  double dailyRentalPrice;
    private String  status;
    private List<CarImageGetDto> images;


}
