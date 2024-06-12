package lk.riyapola.riyapola.dto;

import lk.riyapola.riyapola.entity.CarImg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class CarImageGetDto {
    private Integer imgId;
    private String images;
    private Integer carId;




    public CarImageGetDto(CarImg carImg) {
        this.imgId = carImg.getImgId();
        this.images = carImg.getImages();
        this.carId = carImg.getCar().getCarId();
    }
}
