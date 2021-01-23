package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import umlteacher.model.dao.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
