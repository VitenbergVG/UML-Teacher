package umlteacher.model.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "edu")
@NoArgsConstructor
public class Edu {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "edu_id", length = 2, nullable = false)
    private int id;

    @Column(name = "edu_name", length = 60, nullable = false)
    private String name;

    public Edu(String name) {
        this.name = name;
    }
}
