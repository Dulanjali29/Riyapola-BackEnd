package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
    Admin findAdminByFirstName(String name);
}
