package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

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
}
