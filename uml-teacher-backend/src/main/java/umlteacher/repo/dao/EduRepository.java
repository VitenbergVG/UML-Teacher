package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import umlteacher.model.dao.Edu;

public interface EduRepository extends JpaRepository<Edu, Integer> {
	Edu findById(int id);
}
