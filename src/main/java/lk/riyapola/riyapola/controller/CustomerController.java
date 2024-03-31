package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import lk.riyapola.riyapola.service.CustomerService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("riyapola/customer")
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
    @GetMapping("/getCustomerDetails")
    public ResponseEntity<Object> getCustomerDetails(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Customer customerJwtToken = jwtTokenGenerator.getCustomerFromJwtToken(authorizationHeader);
            return new ResponseEntity<>(customerJwtToken, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Token Invalid  ", HttpStatus.UNAUTHORIZED);
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
@GetMapping("/getAllCars")
public ResponseEntity<Object> getAllCars(){
        try {
          List<Car> allCars=customerService.getAllCars();
          return  new ResponseEntity<>(allCars,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("No Cars",HttpStatus.FORBIDDEN);
        }
  }
    @DeleteMapping ("/deleteCustomerById")
    public ResponseEntity<Object> deletecustomerById(@RequestHeader(name = "Authorization") String authorizationHeader){
        if(jwtTokenGenerator.validateJwtToken(authorizationHeader)){
          Customer customerIdFromJwtToken=jwtTokenGenerator.getCustomerFromJwtToken(authorizationHeader);
          String deleteCustomer=customerService.deleteCustomerDetail(customerIdFromJwtToken);
          return new ResponseEntity<>(deleteCustomer,HttpStatus.CREATED);
        }else {
            return  new ResponseEntity<>("Invalid customer token to get customer Details",HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/registerdCustomer/getAllCars")
    public ResponseEntity<Object> getAllCars(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Car> allCar = customerService.getAllCarByRegisterdCus();
            return new ResponseEntity<>(allCar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token By Customer", HttpStatus.FORBIDDEN);
        }
    }

        @PostMapping("/login")
    public  ResponseEntity<Map<String,String>> customerLogin(@RequestBody CustomerDTO customerDTO){

        BCryptPasswordEncoder decodePassword = new BCryptPasswordEncoder();
        HashMap<String, String> response = new HashMap<>();

        Customer customerByUserName = customerRepo.findCustomerByUserName(customerDTO.getUserName());

        String customerBypassword = customerRepo.passwordByUserName(customerDTO.getUserName());

        if (customerByUserName!= null && decodePassword.matches(customerDTO.getPassword(), customerBypassword)) {
            String token = this.jwtTokenGenerator.generateJwtToken(customerByUserName);
            response.put("token",token);


            return  new ResponseEntity<>(response, HttpStatus.OK);
        } else {

            response.put("massage", "wrong Credentials");
            return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
        }
    }
}
