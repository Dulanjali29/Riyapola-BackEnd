package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.repo.AdminRepo;

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

    @Autowired
    public AdminService(AdminRepo adminRepo, JWTTokenGenerator jwtTokenGenerator) {
        this.adminRepo = adminRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }


    public Admin saveAdmin(AdminDTO adminDTO) {
        if(adminDTO!=null){
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            String enPassword=bCryptPasswordEncoder.encode(adminDTO.getPassword());
            Admin save = adminRepo.save(new Admin(adminDTO.getFirstName(), adminDTO.getLastName(), adminDTO.getUserName(), enPassword, adminDTO.getRole()));
            return  save;
        }

        return null;
    }

    public List<Admin> getAllAdmin() {

//        return adminRepo.findAll();
        try {
            return adminRepo.findAll();
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("Error Axios data");
        }
    }

    public Admin updateAdmin(Integer id, AdminDTO adminDTO) {
        if (adminRepo.existsById(id)) {
            return adminRepo.save(new Admin(id, adminDTO.getFirstName(), adminDTO.getLastName(), adminDTO.getUserName(), adminDTO.getPassword(), adminDTO.getRole()));
        }
        return null;
    }

    public String deleteCustomer(Integer id) {
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

    public HashMap<String, String> loginAdmin( AdminDTO adminDTO) {
        HashMap<String, String> response = new HashMap<>();
        BCryptPasswordEncoder decodePassword=new BCryptPasswordEncoder();
        Admin admin=new Admin();
      boolean matches=  decodePassword.matches(adminDTO.getPassword(),admin.getPassword());
        Admin newAdmin=adminRepo.findByUserName(adminDTO.getUserName());

        if (newAdmin != null && !matches) {
            String token = this.jwtTokenGenerator.generateJwtToken(adminDTO);
            response.put("token", token);
        } else {
            response.put("massage", "wrong Credentials");
        }
        return response;
    }
}
