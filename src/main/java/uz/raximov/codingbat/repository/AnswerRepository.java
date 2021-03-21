package uz.raximov.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.codingbat.entity.Answer;
import uz.raximov.codingbat.entity.Task;
import uz.raximov.codingbat.entity.User;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    boolean existsByTaskAndUser(Task task, User user);

}
