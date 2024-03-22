package lk.riyapola.riyapola.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private int noOfPassengers;
    private String fuelType;
    private String transmissionMode;
    private  double dailyRentalPrice;
    private String  status;

    public Car(String brand, String model, int noOfPassengers, String fuelType, String transmissionMode, double dailyRentalPrice, String status) {
        this.brand = brand;
        this.model = model;
        this.noOfPassengers = noOfPassengers;
        this.fuelType = fuelType;
        this.transmissionMode = transmissionMode;
        this.dailyRentalPrice = dailyRentalPrice;
        this.status = status;
    }
}
