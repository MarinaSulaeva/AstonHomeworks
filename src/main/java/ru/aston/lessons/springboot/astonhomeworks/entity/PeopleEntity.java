package ru.aston.lessons.springboot.astonhomeworks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name = "people")
@NoArgsConstructor
@Getter
@Setter
public abstract class PeopleEntity {
    @Id
    @SequenceGenerator(name = "people_seq",
            sequenceName = "people_sequence",
            initialValue = 50, allocationSize = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "people_seq")
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
}
