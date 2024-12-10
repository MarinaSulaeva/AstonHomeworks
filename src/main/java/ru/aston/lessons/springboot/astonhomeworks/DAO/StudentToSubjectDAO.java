package ru.aston.lessons.springboot.astonhomeworks.DAO;

import ru.aston.lessons.springboot.astonhomeworks.model.StudentToSubject;
import ru.aston.lessons.springboot.astonhomeworks.model.Subject;

import java.util.List;

public interface StudentToSubjectDAO {

    void save(StudentToSubject studentToSubject);
    void update(StudentToSubject studentToSubject);
    List<Subject> findByStudent(int idStudent);
    void deleteByStudent(int idStudent);
    void deleteBySubject(int idSubject);


}
