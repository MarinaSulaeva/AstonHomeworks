package ru.aston.lessons.springboot.astonhomeworks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    private Subject subject;
}
