package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import umlteacher.model.dao.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    Group findById(int id);

    Group findByCurrentCourseId(int currentCourseId);
}
