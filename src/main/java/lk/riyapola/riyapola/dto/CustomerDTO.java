package lk.riyapola.riyapola.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer customer_id;
    private String firstName;
    private  String lastName;
    private  String nic;
    private String address;
    private String contact;
    private String email;
    private  String userName;
    private  String password;
    private String dateTime;
}
