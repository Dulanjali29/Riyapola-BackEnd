package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import lk.riyapola.riyapola.service.CustomerService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private  final CustomerService customerService;
    private  final JWTTokenGenerator jwtTokenGenerator;
    private final CustomerRepo customerRepo;
@Autowired
    public CustomerController(CustomerService customerService, JWTTokenGenerator jwtTokenGenerator, CustomerRepo customerRepo) {
        this.customerService = customerService;
    this.jwtTokenGenerator = jwtTokenGenerator;
    this.customerRepo = customerRepo;
}
    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO){
   Customer cus= customerService.registerCustomer(customerDTO);
   return  new ResponseEntity<>(cus, HttpStatus.CREATED);
    }

    @GetMapping
   public ResponseEntity<List<Customer>> getAllCustomer(){
    List <Customer> allCustomers=customerService.getAllCustomer();
    return  new ResponseEntity<>(allCustomers,HttpStatus.OK);
    }
    @PutMapping("/{customerId}")
    public  ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId,@RequestBody CustomerDTO customerDTO){
     Customer customer=customerService.updateCustomer(customerId,customerDTO);
    return  new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId){
    String output=customerService.deleteCustomer(customerId);
    return  new ResponseEntity<>(output,HttpStatus.CREATED);
    }

    @GetMapping("/search_customer/{customerId}")
    public  ResponseEntity<Customer>searchCustomerById(@PathVariable Integer customerId){
    Customer customer=customerService.searchCustomerById(customerId);
    return  new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("/search_customer_name/{customerName}")
    public  ResponseEntity<Customer>searchCustomerByName(@PathVariable String customerName){
        Customer customer=customerService.searchCustomerByName(customerName);
        return  new ResponseEntity<>(customer,HttpStatus.OK);
    }
}
