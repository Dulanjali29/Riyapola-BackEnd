package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.service.CustomerService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private  final CustomerService customerService;
    private  final JWTTokenGenerator jwtTokenGenerator;
@Autowired
    public CustomerController(CustomerService customerService, JWTTokenGenerator jwtTokenGenerator) {
        this.customerService = customerService;
    this.jwtTokenGenerator = jwtTokenGenerator;
}
    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody CustomerDTO customerDTO){
   Customer cus= customerService.saveCustomer(customerDTO);
   return  new ResponseEntity<>(cus, HttpStatus.CREATED);
    }
}
