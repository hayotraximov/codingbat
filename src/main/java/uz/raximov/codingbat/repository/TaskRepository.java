package uz.raximov.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.codingbat.entity.Category;
import uz.raximov.codingbat.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByNameAndCategory(String name, Category category);
}
