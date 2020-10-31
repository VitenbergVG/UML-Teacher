package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import umlteacher.model.dao.EducationMaterial;

public interface EducationMaterialRepository extends JpaRepository<EducationMaterial, Integer> {
}
