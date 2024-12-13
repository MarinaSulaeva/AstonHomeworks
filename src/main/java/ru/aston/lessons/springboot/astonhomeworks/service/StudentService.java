package ru.aston.lessons.springboot.astonhomeworks.service;

import ru.aston.lessons.springboot.astonhomeworks.dto.StudentCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentDTO;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;


import java.util.List;

public interface StudentService {
    StudentDTO addStudent(StudentCreate studentCreate);
    List<StudentDTO> getStudents();
    StudentDTO getStudent(int id);
    StudentDTO updateStudent(StudentDTO studentDTO);
    void deleteStudent(int id);
    List<SubjectDTO> getStudentsSubjects(int id);
}
