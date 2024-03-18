package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.AdminRepo;
import lk.riyapola.riyapola.service.AdminService;

import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final AdminRepo adminRepo;
    private final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public AdminController(AdminService adminService, AdminRepo adminRepo, JWTTokenGenerator jwtTokenGenerator) {

        this.adminService = adminService;
        this.adminRepo = adminRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/saveAdmin")
    public ResponseEntity<Admin> saveAdmin(@RequestBody AdminDTO adminDTO) {
        Admin admin = adminService.saveAdmin(adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @GetMapping("/getAllAdmin")
    public ResponseEntity<Object> getAdmin(@RequestHeader(name="Authorization") String authorizationHeader) {
        if(jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            List<Admin> allAdmins = adminService.getAllAdmin();
            return new ResponseEntity<>(allAdmins, HttpStatus.OK);
        }else {
        return  new ResponseEntity<>("Invalid token By Admin",HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping("/updateAdmin/{adminId}")
    public ResponseEntity<Object> updateAdmin(@PathVariable Integer adminId, @RequestBody AdminDTO adminDTO,@RequestHeader(name="Authorization") String authorizationHeader) {
        if(jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            Admin admin = adminService.updateAdmin(adminId, adminDTO);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
        return  new ResponseEntity<>("Invalid token By Admin",HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/deleteAdmin/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer adminId) {
        String output = adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @GetMapping("/search_admin/{adminId}")
    public ResponseEntity<Admin> searchAdmin(@PathVariable Integer adminId) {
        Admin admin = adminService.searchAdmin(adminId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/search_admin_name/{adminName}")
    public ResponseEntity<Admin> searchAdmin(@PathVariable String adminName) {
        Admin admin = adminService.searchAdminByName(adminName);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @GetMapping("/getAllCustomers")
    public ResponseEntity<Object> getAllCustomer(@RequestHeader(name="Authorization") String authorizationHeader) {
        if(jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            List<Customer> allCustomers = adminService.getAllCustomers();
            return new ResponseEntity<>(allCustomers, HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("Invalid token By Customers",HttpStatus.FORBIDDEN);
        }

    }
    @PostMapping("/login")
    public  ResponseEntity<HashMap<String,String>> adminLogin(@RequestBody AdminDTO adminDTO){

        BCryptPasswordEncoder decodePassword = new BCryptPasswordEncoder();
        HashMap<String, String> response = new HashMap<>();

        Admin adminByUserName = adminRepo.findAdminByUserName(adminDTO.getUserName());

        String adminBypassword = adminRepo.passwordByUserName(adminDTO.getUserName());



        if (adminByUserName!= null && decodePassword.matches(adminDTO.getPassword(), adminBypassword)) {
            String token = this.jwtTokenGenerator.generateJwtToken(adminByUserName);
            response.put("token", token);
            return  new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("massage", "wrong Credentials");
            return  new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }


}
