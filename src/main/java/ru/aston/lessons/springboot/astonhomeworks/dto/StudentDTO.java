package ru.aston.lessons.springboot.astonhomeworks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private int id;
    private String firstName;
    private String lastName;
    private List<SubjectDTO> subjectDTOList;
}
