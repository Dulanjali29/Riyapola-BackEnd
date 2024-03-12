package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {

        this.customerRepo = customerRepo;
    }

    public Customer registerCustomer(CustomerDTO customerDTO) {

        Customer save = customerRepo.save(new Customer(customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getUserName(), customerDTO.getPassword(), customerDTO.getDateTime()));
        return save;
    }
    public List<Customer> getAllCustomer(){
        return customerRepo.findAll();
    }
    public  Customer updateCustomer(Integer id,CustomerDTO customerDTO){
        if(customerRepo.existsById(id)){
           return  customerRepo.save(new Customer(id,customerDTO.getFirstName(),customerDTO.getLastName(),customerDTO.getUserName(),customerDTO.getPassword(),customerDTO.getDateTime()));
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
}
