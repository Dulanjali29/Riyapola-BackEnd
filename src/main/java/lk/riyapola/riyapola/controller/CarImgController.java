package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.CarImageGetDto;
import lk.riyapola.riyapola.dto.CarImgDTO;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.entity.CarImg;
import lk.riyapola.riyapola.service.CarImgService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by dulanjali
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.controller
 * Date : 5/28/2024
 * Time :3:58 PM
 */
@CrossOrigin
@RestController
@RequestMapping("/riyapola/image")

public class CarImgController {
    private final CarImgService carImgService;
    private  final JWTTokenGenerator jwtTokenGenerator;


    @Autowired
    public CarImgController(CarImgService carImgService, JWTTokenGenerator jwtTokenGenerator) {
        this.carImgService = carImgService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/addImage")

    public ResponseEntity<Object> saveImage(@ModelAttribute CarImgDTO carImgDTO, @RequestHeader(name = "Authorization") String authorizationHeader)throws URISyntaxException, IOException {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {

            System.out.println("id 1 -"+carImgDTO.getCarId());
           CarImageGetDto carImageGetDto = carImgService.saveImage(carImgDTO);

           return new ResponseEntity<>(carImageGetDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid token By Admin", HttpStatus.FORBIDDEN);
        }
    }

}

