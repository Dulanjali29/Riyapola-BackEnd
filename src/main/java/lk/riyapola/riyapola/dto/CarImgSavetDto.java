package lk.riyapola.riyapola.dto;

import lk.riyapola.riyapola.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarImgSavetDto {
private  int imgId;
private String image;
private Car car_Id;

    public CarImgSavetDto(Integer imgId, String image,Car car) {
        this.imgId = imgId;
        this.image = image;
        this.car_Id=car;
    }

    public CarImgSavetDto(Car car_Id) {
        this.car_Id = car_Id;
    }
}
