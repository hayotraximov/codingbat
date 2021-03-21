package uz.raximov.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.codingbat.entity.ProgLang;
import uz.raximov.codingbat.entity.StarBadge;

public interface StarBadgeRepository extends JpaRepository<StarBadge, Integer> {
    boolean existsByScoreAndProgLang(Integer score, ProgLang progLang);
    boolean existsByScoreAndProgLangAndIdNot(Integer score, ProgLang progLang, Integer id);
}
