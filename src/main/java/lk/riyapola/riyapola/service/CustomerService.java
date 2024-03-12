package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer saveCustomer(CustomerDTO customerDTO) {
        String encodePassword = Base64.getEncoder().encodeToString(customerDTO.getPassword().getBytes());

        Customer save = customerRepo.save(new Customer(customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getNic(), customerDTO.getAddress(), customerDTO.getContact(), customerDTO.getEmail(), customerDTO.getUserName(), encodePassword, customerDTO.getDateTime()));
        return save;
    }
}
