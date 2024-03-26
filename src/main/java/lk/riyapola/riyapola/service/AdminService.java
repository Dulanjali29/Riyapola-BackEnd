package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.AdminRepo;

import lk.riyapola.riyapola.repo.CustomerRepo;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepo adminRepo;
    private final JWTTokenGenerator jwtTokenGenerator;
private  final CustomerRepo customerRepo;
    @Autowired
    public AdminService(AdminRepo adminRepo, JWTTokenGenerator jwtTokenGenerator, CustomerRepo customerRepo) {
        this.adminRepo = adminRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.customerRepo = customerRepo;
    }


    public Admin saveAdmin(AdminDTO adminDTO) {
        if (adminDTO != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String enPassword = bCryptPasswordEncoder.encode(adminDTO.getPassword());
            Admin save = adminRepo.save(new Admin(adminDTO.getFirstName(), adminDTO.getLastName(), adminDTO.getUserName(), enPassword, adminDTO.getRole()));
            return save;
        }

        return null;
    }

    public List<Admin> getAllAdmin() {

//        return adminRepo.findAll();
        try {
            return adminRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error Axios data");
        }
    }

    public Admin updateAdmin(Integer id, AdminDTO adminDTO) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        if (adminRepo.existsById(id)) {
            String newpassword;
            Admin admin=adminRepo.findById(id).get();
            if(adminDTO.getPassword()==null){
                newpassword=admin.getPassword();
            }else {
               String enPassword=passwordEncoder.encode(adminDTO.getPassword());
                newpassword=enPassword;
            }

            return adminRepo.save(new Admin(id,
                    adminDTO.getFirstName(),
                    adminDTO.getLastName(), adminDTO.getUserName(),
                    newpassword,
                    adminDTO.getRole()));
        }
        return null;
    }

    public String deleteAdmin(Integer id) {
        if (adminRepo.existsById(id)) {
            adminRepo.deleteById(id);
            return "Admin Deleted!";
        }
        return "No Admin Found!";
    }

    public Admin searchAdmin(Integer id) {
        Optional<Admin> byId = adminRepo.findById(id);
        return byId.orElse(null);
    }

    public Admin searchAdminByName(String name) {

        return adminRepo.findAdminByFirstName(name);
    }


    public List<Customer> getAllCustomers(){
        List<Customer> allCustomer=customerRepo.findAll();
        return  allCustomer;
    }
    public String deleteCustomer(Integer id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return "Customer Deleted!";
        }
        return "No Customer Found!";
    }
}
