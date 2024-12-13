package ru.aston.lessons.springboot.astonhomeworks.DAOHibernate;

import ru.aston.lessons.springboot.astonhomeworks.entity.StudentEntity;
import ru.aston.lessons.springboot.astonhomeworks.entity.SubjectEntity;


import java.util.List;
import java.util.Optional;

public interface StudentEntityDAO {
    void createStudent(StudentEntity student);
    void updateStudent(StudentEntity student);
    Optional<StudentEntity> getStudent(int id);
    void deleteStudent(int id);
    List<StudentEntity> getStudents();
    List<SubjectEntity> getStudentsSubjects(int id);

}
