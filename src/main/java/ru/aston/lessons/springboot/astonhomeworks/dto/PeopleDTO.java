package ru.aston.lessons.springboot.astonhomeworks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PeopleDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String className;
}
