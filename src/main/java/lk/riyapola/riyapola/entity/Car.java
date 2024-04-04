package lk.riyapola.riyapola.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer car_id;
    private String brand;
    private  String  model;
    private String noOfPassengers;
    private String fuelType;
    private String transmissionMode;
    private  double dailyRentalPrice;
    private String  status;
    private  String carName;

   @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
   private List<CarImg> carImgs;

    public Car(String brand, String model, String noOfPassengers, String fuelType, String transmissionMode, double dailyRentalPrice, String status, String carName) {
        this.brand = brand;
        this.model=model;
        this.noOfPassengers=noOfPassengers;
        this.fuelType=fuelType;
        this.transmissionMode=transmissionMode;
        this.dailyRentalPrice=dailyRentalPrice;
        this.status=status;
        this.carName=carName;
    }
}
