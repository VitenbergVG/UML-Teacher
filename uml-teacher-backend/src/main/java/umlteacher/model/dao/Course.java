package umlteacher.model.dao;

import com.vladmihalcea.hibernate.type.interval.PostgreSQLIntervalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.sql.Date;
import java.time.Duration;
import java.util.Set;

@Data
@Entity
@Table(name = "course")
@TypeDef(
		typeClass = PostgreSQLIntervalType.class,
		defaultForType = Duration.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "course_id", length = 4, nullable = false)
    private int id;

    @Column(name = "course_name", nullable = false)
    private String name;
    
    @Column(name = "created_date", nullable = false)
    private Date date;
    
    @Column(name = "rating")
    private Double rating;
    
    @Column(name = "time_to_complete", nullable = false)
    private Duration time;

    @Column(name = "time_to_complete", nullable = false, columnDefinition = "interval")
    private Duration timeToComplete;

    @ManyToMany(mappedBy = "courses")
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "courses")
    private Set<EducationMaterial> educationMaterials;
}
