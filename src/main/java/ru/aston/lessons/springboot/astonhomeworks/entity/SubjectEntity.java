package ru.aston.lessons.springboot.astonhomeworks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "subjects")
@NoArgsConstructor
@Getter
@Setter
public class SubjectEntity {
    @Id
    @SequenceGenerator(name = "subject_seq",
            sequenceName = "subject_sequence",
            initialValue = 5, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_seq")
    @Column(name = "id", nullable = false)
    private int id;
    private String subjectName;
    private String description;
}
