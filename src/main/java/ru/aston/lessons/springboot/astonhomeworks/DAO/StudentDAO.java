package ru.aston.lessons.springboot.astonhomeworks.DAO;

import ru.aston.lessons.springboot.astonhomeworks.model.Student;


import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    void createStudent(Student student);
    void updateStudent(Student student);
    Optional<Student> getStudent(int id);
    void deleteStudent(int id);
    List<Student> getStudents();
    boolean existId(int id);
}
