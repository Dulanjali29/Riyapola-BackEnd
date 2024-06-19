package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CarRepo;
import lk.riyapola.riyapola.repo.CustomerRepo;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CarRepo carRepo;
private  final JWTTokenGenerator jwtTokenGenerator;
    private Customer customerByFirstName;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, CarRepo carRepo, JWTTokenGenerator jwtTokenGenerator) {

        this.customerRepo = customerRepo;
        this.carRepo = carRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public Customer registerCustomer(CustomerDTO customerDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String encodePassword=bCryptPasswordEncoder.encode(customerDTO.getPassword());

        LocalDateTime dateTime=LocalDateTime.now();
        String CurrentDateTime=dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyy hh:mm:ss a "));
       customerDTO.setDateTime(CurrentDateTime);
        System.out.println("Date & Time : "+customerDTO.getDateTime());

        Customer save = customerRepo.save(new Customer(
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getUserName(),
                encodePassword,
                customerDTO.getDateTime()
        ));
        return save;
    }
    public List<Customer> getAllCustomer(){

        return customerRepo.findAll();
    }
    public  Customer updateCustomer(Integer id,CustomerDTO customerDTO){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String encodePassword=bCryptPasswordEncoder.encode(customerDTO.getPassword());

        LocalDateTime dateTime=LocalDateTime.now();
        String CurrentDateTime=dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyy hh:mm:ss a"));
        customerDTO.setDateTime(CurrentDateTime);
        System.out.println("Date & Time : "+customerDTO.getDateTime());
        if(customerRepo.existsById(id)){
           return  customerRepo.save(new Customer(
                    id,
                   customerDTO.getFirstName(),
                   customerDTO.getLastName(),
                   customerDTO.getNic(),
                   customerDTO.getAddress(),
                   customerDTO.getContact(),
                   customerDTO.getEmail(),
                   customerDTO.getUserName(),
                   customerDTO.getPassword(),
                   customerDTO.getDateTime()
           ));
        }
        return  null;
    }
    public String deleteCustomer(Integer id){
        if(customerRepo.existsById(id)){
            customerRepo.deleteById(id);
            return "Delete Customer!";
        }
        return "No customerFound!";
    }
    public  Customer searchCustomerById(Integer id){
        Optional<Customer> byId=customerRepo.findById(id);
        return byId.orElse(null);
    }
    public Customer searchCustomerByName(String name){

        return customerByFirstName;
    }
    public List<Car> getAllCars(){
    List <Car> cars=carRepo.findAll();
    return  cars;
    }
    public List<Car> getAllCarByRegisterdCus(){
        List <Car> cars=carRepo.findAll();
        return  cars;
    }
    public String deleteCustomerDetail(Customer customerIdFromJwtToken){
        if(customerRepo.existsById(customerIdFromJwtToken.getCustomer_id())){
            customerRepo.deleteById(customerIdFromJwtToken.getCustomer_id());
            return "customer details delete successful!";
        }else {
            return "Customer Id"+customerIdFromJwtToken.getCustomer_id()+"not found";
        }
    }
    public Customer updateCustomerById(Customer customerFromJwtToken,CustomerDTO customerDTO){
        if(customerRepo.existsById(Integer.valueOf(customerFromJwtToken.getCustomer_id()))){
         Customer customer=customerRepo.findById(customerFromJwtToken.getCustomer_id()).orElse(null);
         if(customer!=null){
             customer.setFirstName(customerDTO.getFirstName());
             customer.setLastName(customerDTO.getLastName());
             customer.setNic(customerDTO.getNic());
             customer.setAddress(customerDTO.getAddress());
             customer.setContact(customerDTO.getContact());
             customer.setEmail(customerDTO.getEmail());
             customer.setDateTime(customerFromJwtToken.getDateTime());
             System.out.println(customerFromJwtToken.getDateTime());
             customerRepo.save(customer);
             return  customer;

            } else {
             System.out.println("Customer with id not found ");
         }

        }
        return  null;
    }
    public Customer updateCustomerUserNamePasswordById(Customer customerFromJwtToken,CustomerDTO customerDTO){

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String encodePassword=bCryptPasswordEncoder.encode(customerDTO.getPassword());

        if(customerRepo.existsById(customerFromJwtToken.getCustomer_id())){
            Customer customer=customerRepo.findById(customerFromJwtToken.getCustomer_id()).orElse(null);
            if(customer!=null){
                customer.setUserName(customerDTO.getUserName());
                customer.setPassword(encodePassword);
                System.out.println(customerFromJwtToken.getDateTime());
                customerRepo.save(customer);
                return  customer;

            } else {
                System.out.println("Customer with id not found ");
            }

        }
        return  null;
    }
    public HashMap<String,String> loginCustomer(CustomerDTO customerDTO){
    HashMap<String,String> response=new HashMap<>();
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    List<Customer> allByCustomerName=customerRepo.findCustomerByUserName(customerDTO.getUserName());

    for(Customer customer: allByCustomerName){
      boolean match=  passwordEncoder.matches(customerDTO.getPassword(),customer.getPassword());
      if(match){
          String token=this.jwtTokenGenerator.generateJwtToken(customer);
          response.put("token",token);
          Integer cusId= customer.getCustomer_id();
          response.put("cusId", String.valueOf(cusId));
          System.out.println(cusId);
          return  response;
      }else {
          response.put("massage","Customer Token Generated failed !");

      }
    }
    return  response;
    }
}
