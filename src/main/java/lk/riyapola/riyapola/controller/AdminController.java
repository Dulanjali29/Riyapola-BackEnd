package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
@Autowired
    public AdminController(AdminService adminService) {

        this.adminService = adminService;
    }
    @PostMapping
    public ResponseEntity<Admin> saveAdmin(@RequestBody AdminDTO adminDTO){
       Admin admin= adminService.saveAdmin(adminDTO);
       return  new ResponseEntity<>(admin, HttpStatus.CREATED);
    }
}
