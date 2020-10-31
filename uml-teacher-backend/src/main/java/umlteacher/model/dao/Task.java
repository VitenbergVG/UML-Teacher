package umlteacher.model.dao;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
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

    public Task(String path) {
        this.path = path;
    }
}
