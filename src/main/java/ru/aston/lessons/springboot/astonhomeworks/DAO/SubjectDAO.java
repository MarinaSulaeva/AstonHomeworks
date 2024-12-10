package ru.aston.lessons.springboot.astonhomeworks.DAO;

import ru.aston.lessons.springboot.astonhomeworks.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectDAO {
    void createSubject(Subject subject);
    void updateSubject(Subject subject);
    void deleteSubject(int id);
    Optional<Subject> getSubject(int id);
    List<Subject> getSubjects();
    boolean existId(int id);
}
