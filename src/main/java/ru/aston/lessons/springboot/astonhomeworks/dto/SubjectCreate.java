package ru.aston.lessons.springboot.astonhomeworks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectCreate {
    private String subjectName;
    private String description;
}
