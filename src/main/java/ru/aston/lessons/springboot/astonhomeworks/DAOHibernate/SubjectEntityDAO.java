package ru.aston.lessons.springboot.astonhomeworks.DAOHibernate;

import ru.aston.lessons.springboot.astonhomeworks.entity.SubjectEntity;

import java.util.List;
import java.util.Optional;

public interface SubjectEntityDAO {
    void createSubject(SubjectEntity subject);

    void updateSubject(SubjectEntity subject);

    void deleteSubject(int id);

    Optional<SubjectEntity> getSubject(int id);

    List<SubjectEntity> getSubjects();
}