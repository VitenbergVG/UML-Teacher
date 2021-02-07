package umlteacher.model.dao;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "edumat")
public class EducationMaterial {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "edumat_id", length = 4, nullable = false)
    private int id;

    @Column(name = "edumat_path", length = 255, nullable = false)
    private String path;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "course_edumat",
            joinColumns = {@JoinColumn(name = "edumat_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private Set<Course> courses;
}
