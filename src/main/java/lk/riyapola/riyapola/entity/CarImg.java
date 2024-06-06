package lk.riyapola.riyapola.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarImg {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer imgId;
  private String images;

  @ManyToOne
  @JoinColumn(name = "car_id", nullable = false)
  @JsonIgnore
  private Car car;

  public CarImg(String images, Car car) {
    this.images = images;
    this.car = car;
  }
}
