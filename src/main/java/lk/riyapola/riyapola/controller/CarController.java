package lk.riyapola.riyapola.controller;
import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.dto.CarDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.service.CarService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("riyapola/car")

public class CarController {
    private final CarService carService;
    private final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public CarController(CarService carService, JWTTokenGenerator jwtTokenGenerator) {
        this.carService = carService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/carRegister")
    public ResponseEntity<Object> registerCar(@RequestBody CarDTO carDTO, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Car car = carService.saveCar(carDTO);
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid token By Admin", HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/getAllCar")
    public ResponseEntity<Object> getAllCar(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Car> allCars = carService.getAllCar();
            return new ResponseEntity<>(allCars, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token By Admin", HttpStatus.FORBIDDEN);
        }


    }
    @PutMapping("/updateCar/{carId}")
    public ResponseEntity<Object> updateCar(@PathVariable Integer carId, @RequestBody CarDTO carDTO, @RequestHeader(name="Authorization") String authorizationHeader) {
        if(jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            Car car = carService.updateCar(carId, carDTO);
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return  new ResponseEntity<>("Invalid token By Admin",HttpStatus.FORBIDDEN);
    }
    @DeleteMapping("/deleteCar/{carId}")
    public ResponseEntity<Object> deleteCar(@PathVariable Integer carId ,@RequestHeader(name="Authorization") String authorizationHeader) {
        if(jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            String deleted = carService.deleteCar(carId);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        }
        return  new ResponseEntity<>("Invalid token By Admin",HttpStatus.FORBIDDEN);
    }

}
