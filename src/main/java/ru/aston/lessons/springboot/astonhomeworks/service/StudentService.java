package ru.aston.lessons.springboot.astonhomeworks.service;

import ru.aston.lessons.springboot.astonhomeworks.dto.StudentCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentDTO;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    void addStudent(StudentCreate studentCreate);
    List<StudentDTO> getStudents();
    StudentDTO getStudent(int id) throws StudentNotFoundException;
    void updateStudent(StudentDTO studentDTO) throws StudentNotFoundException;
    void deleteStudent(int id) throws StudentNotFoundException;
    List<SubjectDTO> getStudentsSubjects(int id) throws StudentNotFoundException;
}
