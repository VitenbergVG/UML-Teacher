package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import umlteacher.model.dao.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
