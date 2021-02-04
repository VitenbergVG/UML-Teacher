package umlteacher.service.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import umlteacher.exceptions.EduNotFoundException;
import umlteacher.model.dao.Edu;
import umlteacher.repo.dao.EduRepository;

@Service
public class EduService {
	@Autowired
	EduRepository eduRepository;
	
	public Edu findById(int edu_id) throws EduNotFoundException {
		Edu edu = eduRepository.findById(edu_id);
		if (Objects.isNull(edu)) {
			throw new EduNotFoundException(edu_id);
		}
		return edu;
	}
	
	public List<Edu> findAll() {
		return eduRepository.findAll();
	}
	
	public Edu save(Edu edu) {
		return eduRepository.save(edu);
	}
	
	public void delete(int edu_id) {
		eduRepository.deleteById(edu_id);
	}

}
