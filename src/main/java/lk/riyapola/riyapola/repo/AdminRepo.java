package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
    Admin findAdminByFirstName(String name);

    Admin findByUserName(String userName);

    Admin findBypassword(String userName);

}

