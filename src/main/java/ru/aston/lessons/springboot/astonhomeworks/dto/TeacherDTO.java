package ru.aston.lessons.springboot.astonhomeworks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private int id;
    private String firstName;
    private String lastName;
    private SubjectDTO subjectDTO;
}
