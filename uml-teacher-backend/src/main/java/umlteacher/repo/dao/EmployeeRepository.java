package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import umlteacher.model.dao.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findById(int employee_id);
	
	@Query(value = "SELECT * FROM employee WHERE user_id = ?1", nativeQuery = true)
	Employee findByUserId(long user_id);
}
