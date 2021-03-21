package uz.raximov.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.codingbat.entity.Category;
import uz.raximov.codingbat.entity.ProgLang;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByNameAndProgLang(String name, ProgLang progLang);
    boolean existsByNameAndProgLangAndIdNot(String name, ProgLang progLang, Integer id);
}
