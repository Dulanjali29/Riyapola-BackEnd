package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Integer> {


//    Admin findByUserNameAndPassword(String userName,String password);

    Admin findAdminByUserName(String userName);


    @Query(nativeQuery = true,value = "SELECT password FROM admin WHERE user_name= :userName")
   String passwordByUserName(String userName);

    Admin findAdminByFirstName(String fistName);
}

