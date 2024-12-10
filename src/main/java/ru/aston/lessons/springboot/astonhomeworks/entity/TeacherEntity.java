package ru.aston.lessons.springboot.astonhomeworks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
