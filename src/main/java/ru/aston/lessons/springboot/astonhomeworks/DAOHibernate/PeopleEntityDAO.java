package ru.aston.lessons.springboot.astonhomeworks.DAOHibernate;

import ru.aston.lessons.springboot.astonhomeworks.entity.PeopleEntity;

import java.util.List;

public interface PeopleEntityDAO {

    List<PeopleEntity> getAllPeopleInSchool();
}
