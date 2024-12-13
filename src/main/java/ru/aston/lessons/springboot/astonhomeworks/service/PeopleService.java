package ru.aston.lessons.springboot.astonhomeworks.service;

import ru.aston.lessons.springboot.astonhomeworks.dto.PeopleDTO;

import java.util.List;

public interface PeopleService {
    List<PeopleDTO> getAllPeopleAtSchool();
}
