package umlteacher.model.dao;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Data
@Entity
@Table(name = "edu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Edu {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "edu_id", length = 2, nullable = false)
    private int id;

    @Column(name = "edu_name", length = 60, nullable = false)
    private String name;

}
