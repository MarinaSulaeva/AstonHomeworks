package ru.aston.lessons.springboot.astonhomeworks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
public class StudentEntity extends PeopleEntity{
    @Column(nullable = true)
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "subjects_students",
            joinColumns = @JoinColumn(name = "students_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "subjects_id", referencedColumnName="id")
    )
    private List<SubjectEntity> subjects;
}
