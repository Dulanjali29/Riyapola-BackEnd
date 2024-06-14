package lk.riyapola.riyapola.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lk.riyapola.riyapola.dto.CarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "carId")

public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;
    private String brand;
    private String model;
    private int noOfPassengers;
    private String fuelType;
    private String transmissionMode;
    private double dailyRentalPrice;
    private String status;

    @OneToMany(mappedBy = "car", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
    @ToString.Exclude
    List<CarImg> carImgs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carId")
    List<Reservation>reservations=new ArrayList<>();

    public Car(Integer carId, String brand, String model, int noOfPassengers, String fuelType, String transmissionMode, double dailyRentalPrice, String status) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.noOfPassengers = noOfPassengers;
        this.fuelType = fuelType;
        this.transmissionMode = transmissionMode;
        this.dailyRentalPrice = dailyRentalPrice;
        this.status = status;
    }

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
