package umlteacher.model.dao;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "course_id", length = 4, nullable = false)
    private int id;

    @Column(name = "course_name", length = 40, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "courses")
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "courses")
    private Set<EducationMaterial> educationMaterials;
}
