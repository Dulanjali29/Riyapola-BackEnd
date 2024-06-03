package lk.riyapola.riyapola.dto;

import lk.riyapola.riyapola.entity.CarImg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Created by dulanjali
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.dto
 * Date : 5/30/2024
 * Time :11:16 PM
 */
@AllArgsConstructor
@Data
@NoArgsConstructor


public class CarImageGetDto {
    private  Integer imgId;
    private String images;
    private Integer carId;


    public CarImageGetDto(CarImg saved) {
    }
}
