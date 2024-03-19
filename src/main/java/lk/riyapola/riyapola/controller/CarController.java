package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.CarDTO;
import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.service.CarService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")

public class CarController {
   private  final CarService carService;
   private  final JWTTokenGenerator jwtTokenGenerator;
@Autowired
    public CarController(CarService carService, JWTTokenGenerator jwtTokenGenerator) {
        this.carService = carService;
    this.jwtTokenGenerator = jwtTokenGenerator;
}

    @PostMapping("/carRegister")
    public ResponseEntity<Object> registerCar(@RequestBody CarDTO carDTO, @RequestHeader(name="Authorization") String authorizationHeader) {
    if(jwtTokenGenerator.validateJwtToken(authorizationHeader)){
        Car car = carService.saveCar(carDTO);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }else {
        return  new ResponseEntity<>("Invalid token By Admin",HttpStatus.FORBIDDEN);
    }

    }
}
