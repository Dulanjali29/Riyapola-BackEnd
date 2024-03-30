package lk.riyapola.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarDetailsGetDto {
    private Integer car_id;
    private String brand;
    private  String  model;
    private String noOfPassengers;
    private String fuelType;
    private String transmissionMode;
    private  double dailyRentalPrice;
    private String  status;
    private String carName;
}
