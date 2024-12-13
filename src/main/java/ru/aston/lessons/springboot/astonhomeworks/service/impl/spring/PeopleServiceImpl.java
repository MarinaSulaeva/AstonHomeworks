package ru.aston.lessons.springboot.astonhomeworks.service.impl.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.lessons.springboot.astonhomeworks.dto.PeopleDTO;
import ru.aston.lessons.springboot.astonhomeworks.mapper.PeopleMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.repository.PeopleRepository;
import ru.aston.lessons.springboot.astonhomeworks.service.PeopleService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    @Override
    public List<PeopleDTO> getAllPeopleAtSchool() {
        return PeopleMapperImpl.toDTO(peopleRepository.findAll());
    }
}
