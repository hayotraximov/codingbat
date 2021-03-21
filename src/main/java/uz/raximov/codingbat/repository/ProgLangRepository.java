package uz.raximov.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.codingbat.entity.ProgLang;

public interface ProgLangRepository extends JpaRepository<ProgLang, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
