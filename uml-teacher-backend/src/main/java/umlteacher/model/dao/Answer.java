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
@Table(name = "answer")
public class Answer {
	
	@Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "answer_id", length = 4, nullable = false)
    private int id;
	
	@Column(name = "student_id")
	private int student_id;
	
	@Column(name = "task_id")
	private int task_id;
	
	@Column(name = "course_id")
	private int course_id;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "correct", nullable = true)
	private Boolean isCorrect;

}
