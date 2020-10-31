package umlteacher.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "group_id", length = 4, nullable = false)
    private int id;

    @Column(name = "group_name", length = 30, nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "current_course_id", nullable = false)
    private Course currentCourse;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "first_teacher_id", nullable = false)
    private Employee firstTeacher;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "second_teacher_id")
    private Employee secondTeacher;

    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    private Set<Student> students;
}
