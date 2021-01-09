package umlteacher.model.dao;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "employee_id", length = 4, nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "employee_hiring_date", length = 10, nullable = false)
    private String hiringDate;

    @Column(name = "employee_phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "employee_email", length = 20, nullable = false)
    private String email;
}
