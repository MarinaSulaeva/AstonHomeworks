package ru.aston.lessons.springboot.astonhomeworks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@NoArgsConstructor
@Getter
@Setter
public class TeacherEntity extends PeopleEntity{

    @ManyToOne
    @JoinColumn(name = "subjects_id")
    private SubjectEntity subject;
}
