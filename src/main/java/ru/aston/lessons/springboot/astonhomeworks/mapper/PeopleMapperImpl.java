package ru.aston.lessons.springboot.astonhomeworks.mapper;

import ru.aston.lessons.springboot.astonhomeworks.dto.PeopleDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.PeopleEntity;

import java.util.ArrayList;
import java.util.List;

public class PeopleMapperImpl {
    public static List<PeopleDTO> toDTO(List<PeopleEntity> peoples) {
        List<PeopleDTO> dtos = new ArrayList<>();
        for (PeopleEntity people : peoples) {
            dtos.add(toDTO(people));
        }
        return dtos;
    }

    public static PeopleDTO toDTO(PeopleEntity people) {
        PeopleDTO dto = new PeopleDTO();
        dto.setId(people.getId());
        dto.setFirstName(people.getFirstName());
        dto.setLastName(people.getLastName());
        dto.setClassName(people.getClass().toString());
        return dto;
    }
}
