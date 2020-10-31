package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import umlteacher.model.dao.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
