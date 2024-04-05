package lk.riyapola.riyapola.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarDTO {
    private Integer car_id;
    private String brand;
    private  String  model;
    private String noOfPassengers;
    private String fuelType;
    private String transmissionMode;
    private  double dailyRentalPrice;
    private String  status;
    private MultipartFile image;

//    public CarDTO(String brand, String model, String noOfPassengers, String fuelType, String transmissionMode, double dailyRentalPrice, String status,MultipartFile image) {
//        this.brand = brand;
//        this.model = model;
//        this.noOfPassengers = noOfPassengers;
//        this.fuelType = fuelType;
//        this.transmissionMode = transmissionMode;
//        this.dailyRentalPrice = dailyRentalPrice;
//        this.status = status;
//        this.image=image;
//    }
}
