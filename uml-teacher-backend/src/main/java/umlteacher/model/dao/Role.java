package umlteacher.model.dao;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "role_id", length = 1, nullable = false)
    private int id;

    /**
     * The role name must match the pattern: "ROLE_NAME",
     * for example: ROLE_USER.
     */
    @Column(name = "role_name", length = 30, nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
