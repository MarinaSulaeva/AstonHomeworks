package ru.aston.lessons.springboot.astonhomeworks.DAOHibernate;

import ru.aston.lessons.springboot.astonhomeworks.entity.TeacherEntity;


import java.util.List;
import java.util.Optional;

public interface TeacherEntityDAO {
    void addTeacher(TeacherEntity teacher);
    void updateTeacher(TeacherEntity teacher);
    void deleteTeacher(int id);
    Optional<TeacherEntity> getTeacher(int id);
    List<TeacherEntity> getTeachers();

}
