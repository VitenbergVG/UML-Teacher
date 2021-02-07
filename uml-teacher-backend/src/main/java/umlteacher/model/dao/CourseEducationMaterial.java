package umlteacher.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "course_edumat")
public class CourseEducationMaterial {
	@Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 4, nullable = false)
    private int id;
	
    @Column(name = "course_id", nullable = false)
	private int course_id;
	
	@Column(name = "edumat_id", nullable = false)
	private int edumat_id;
	
	@Column(name = "edumat_number")
	private byte number;
}
