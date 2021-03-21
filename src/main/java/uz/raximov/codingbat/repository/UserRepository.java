package uz.raximov.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.codingbat.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Integer id);
}
