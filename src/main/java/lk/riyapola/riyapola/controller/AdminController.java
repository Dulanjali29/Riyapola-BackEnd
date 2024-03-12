package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.repo.AdminRepo;
import lk.riyapola.riyapola.service.AdminService;

import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    @PostMapping
    public ResponseEntity<Admin> saveAdmin(@RequestBody AdminDTO adminDTO) {
        Admin admin = adminService.saveAdmin(adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmin() {
        List<Admin> allAdmins = adminService.getAllAdmin();
        return new ResponseEntity<>(allAdmins, HttpStatus.OK);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer adminId, @RequestBody AdminDTO adminDTO) {
        Admin admin = adminService.updateAdmin(adminId, adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer adminId) {
        String output = adminService.deleteCustomer(adminId);
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

    @PostMapping("/login")
    public  ResponseEntity<HashMap<String,String>> adminLogin(@RequestBody AdminDTO adminDTO){
      HashMap<String,String> res=adminService.adminLogin(adminDTO);
      return  new ResponseEntity<>(res,HttpStatus.CREATED);
    }


}
