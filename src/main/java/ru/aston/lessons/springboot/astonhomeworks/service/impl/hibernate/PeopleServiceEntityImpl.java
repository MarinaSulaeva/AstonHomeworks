package ru.aston.lessons.springboot.astonhomeworks.service.impl.hibernate;

import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.PeopleEntityDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.impl.PeopleEntityDAOImpl;
import ru.aston.lessons.springboot.astonhomeworks.dto.PeopleDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.PeopleEntity;
import ru.aston.lessons.springboot.astonhomeworks.mapper.PeopleMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.service.PeopleService;

import java.util.List;

public class PeopleServiceEntityImpl implements PeopleService {
    @Override
    public List<PeopleDTO> getAllPeopleAtSchool() {
        PeopleEntityDAO peopleEntityDAO = new PeopleEntityDAOImpl();
        List<PeopleEntity> people = peopleEntityDAO.getAllPeopleInSchool();
        return PeopleMapperImpl.toDTO(people);
    }
}
