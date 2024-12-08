package ru.aston.lessons.springboot.astonhomeworks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    private int id;
    private String subjectName;
    private String description;
}
