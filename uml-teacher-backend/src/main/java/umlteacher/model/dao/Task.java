package umlteacher.model.dao;


import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "task_id", length = 4, nullable = false)
    private int id;

    @Column(name = "task_path", length = 255, nullable = false)
    private String path;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "course_task",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private Set<Course> courses;
}
