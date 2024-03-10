package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.repo.AdminRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class AdminService {
    private  final AdminRepo adminRepo;
@Autowired
    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }


    public Admin saveAdmin(AdminDTO adminDTO){
    String encodePassword= Base64.getEncoder().encodeToString(adminDTO.getPassword().getBytes());
     Admin save=   adminRepo.save(new Admin(adminDTO.getFirstName(),adminDTO.getLastName(),adminDTO.getUserName(),encodePassword,adminDTO.getRole()));
    return  save;
    }
    public List<Admin>  getAllAdmin(){
    return  adminRepo.findAll();
    }
    public Admin updateAdmin(Integer id,AdminDTO adminDTO){
    if(adminRepo.existsById(id)){
        return  adminRepo.save(new Admin(id,adminDTO.getFirstName(),adminDTO.getLastName(),adminDTO.getUserName(),adminDTO.getPassword(),adminDTO.getRole()));
    }
    return  null;
    }
    public  String deleteCustomer(Integer id){
    if(adminRepo.existsById(id)){
        adminRepo.deleteById(id);
        return "Admin Deleted!";
    }
    return  "No Admin Found!";
    }
}
