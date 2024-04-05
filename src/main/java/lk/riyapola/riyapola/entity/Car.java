package lk.riyapola.riyapola.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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


   @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
   private List<CarImg> carImgs;

    public Car(String brand, String model, String noOfPassengers, String fuelType, String transmissionMode, double dailyRentalPrice, String status, String originalFilename) {
        this.brand = brand;
        this.model=model;
        this.noOfPassengers=noOfPassengers;
        this.fuelType=fuelType;
        this.transmissionMode=transmissionMode;
        this.dailyRentalPrice=dailyRentalPrice;
        this.status=status;

    }

    public Car(Integer car_id, String brand, String model, String noOfPassengers, String fuelType, String transmissionMode, double dailyRentalPrice, String status) {
        this.car_id = car_id;
        this.brand = brand;
        this.model = model;
        this.noOfPassengers = noOfPassengers;
        this.fuelType = fuelType;
        this.transmissionMode = transmissionMode;
        this.dailyRentalPrice = dailyRentalPrice;
        this.status = status;

    }
}
