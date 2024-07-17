package pl.jablonskanycz.bakery.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeEntity {

    @Id
    @SequenceGenerator(name = "employees_seq", sequenceName = "employees_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_seq")
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "job_startdate")
    private Instant jobStartDate;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private PersonEntity person;
}
