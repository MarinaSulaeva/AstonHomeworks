package ru.aston.lessons.springboot.astonhomeworks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentToSubject {
    private int id;
    private int studentId;
    private int subjectId;
}
