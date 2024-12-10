package ru.aston.lessons.springboot.astonhomeworks.service;

import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherDTO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.TeacherNotFoundException;

import java.util.List;

public interface TeacherService {
    void addTeacher(TeacherCreate teacherCreate);
    void deleteTeacher(int id) throws TeacherNotFoundException;
    void updateTeacher(TeacherDTO teacherDTO) throws TeacherNotFoundException;
    TeacherDTO getTeacher(int id) throws TeacherNotFoundException;
    List<TeacherDTO> getTeachers();
}
