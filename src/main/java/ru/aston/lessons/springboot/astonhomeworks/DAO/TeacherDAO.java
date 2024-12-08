package ru.aston.lessons.springboot.astonhomeworks.DAO;

import ru.aston.lessons.springboot.astonhomeworks.model.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TeacherDAO {
    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(int id);
    Optional<Teacher> getTeacher(int id);
    List<Teacher> getTeachers();
    boolean existId(int id);
}
