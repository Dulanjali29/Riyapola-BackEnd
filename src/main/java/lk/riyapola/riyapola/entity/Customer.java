package lk.riyapola.riyapola.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name  ="customer_id")
    List<Reservation> reservations=new ArrayList<>();



    public Customer(String firstName, String lastName, String userName, String password, String dateTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dateTime =String.valueOf(dateTime) ;
    }

    public Customer(Integer id, String firstName, String lastName, String nic, String address, String contact, String email, String userName, String password, String dateTime) {
    }

    public Customer(String nic, String address, String contact, String email) {
        this.nic = nic;
        this.address = address;
        this.contact = contact;
        this.email = email;
    }
}
