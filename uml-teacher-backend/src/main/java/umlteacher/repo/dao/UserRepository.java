package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import umlteacher.model.dao.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
