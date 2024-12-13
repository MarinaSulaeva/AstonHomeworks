package ru.aston.lessons.springboot.astonhomeworks.service;

import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO addTeacher(TeacherCreate teacherCreate);
    void deleteTeacher(int id);
    TeacherDTO updateTeacher(TeacherDTO teacherDTO);
    TeacherDTO getTeacher(int id);
    List<TeacherDTO> getTeachers();
}
