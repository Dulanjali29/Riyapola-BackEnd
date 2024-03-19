package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
private  final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, JWTTokenGenerator jwtTokenGenerator) {

        this.customerRepo = customerRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public Customer registerCustomer(CustomerDTO customerDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String encodePassword=bCryptPasswordEncoder.encode(customerDTO.getPassword());
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

        return customerRepo.findCustomerByFirstName(name);
    }
    public HashMap<String, String> customerLogin(CustomerDTO customerDTO) {
        HashMap<String, String> response = new HashMap<>();
        Customer customerByUsernameAndPassword = customerRepo.findByUserNameAndPassword(customerDTO.getUserName(), customerDTO.getPassword());
        if (customerByUsernameAndPassword != null) {
            String token = this.jwtTokenGenerator.generateJwtToken(customerDTO);
            response.put("token", token);
        } else {
            response.put("massage", "wrong Credentials");


        }
        return response;
    }
}
