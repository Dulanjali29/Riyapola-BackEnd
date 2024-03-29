package lk.riyapola.riyapola.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
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

    public Customer(String firstName, String lastName, String userName, String password, String dateTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dateTime =String.valueOf(dateTime) ;
    }
}
