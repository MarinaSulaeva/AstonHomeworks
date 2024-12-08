package ru.aston.lessons.springboot.astonhomeworks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherCreate {
    private String firstName;
    private String lastName;
    private SubjectDTO subjectDTO;
}
