package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import lk.riyapola.riyapola.service.CustomerService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final JWTTokenGenerator jwtTokenGenerator;
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerController(CustomerService customerService, JWTTokenGenerator jwtTokenGenerator, CustomerRepo customerRepo) {
        this.customerService = customerService;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.customerRepo = customerRepo;
    }

    @PostMapping("/customerRegister")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer cus = customerService.registerCustomer(customerDTO);
        return new ResponseEntity<>(cus, HttpStatus.CREATED);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<Object> getAllCustomer(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Customer> allCustomers = customerService.getAllCustomer();
            return new ResponseEntity<>(allCustomers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token By Admin", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("customerUpdate/{customerId}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Integer customerId, @RequestBody CustomerDTO customerDTO, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Customer customer = customerService.updateCustomer(customerId, customerDTO);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token By Admin", HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping("customerDelete/{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Integer customerId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if(jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            String output = customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(output, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Invalid token By Admin", HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/search_customer/{customerId}")
    public ResponseEntity<Customer> searchCustomerById(@PathVariable Integer customerId) {
        Customer customer = customerService.searchCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/search_customer_name/{customerName}")
    public ResponseEntity<Customer> searchCustomerByName(@PathVariable String customerName) {
        Customer customer = customerService.searchCustomerByName(customerName);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> customerLogin(@RequestBody CustomerDTO customerDTO) {

        HashMap<String, String> res = customerService.customerLogin(customerDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
