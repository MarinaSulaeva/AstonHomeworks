package ru.aston.lessons.springboot.astonhomeworks.mapper;

import ru.aston.lessons.springboot.astonhomeworks.dto.StudentCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentDTO;
import ru.aston.lessons.springboot.astonhomeworks.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMapperImpl {
    public static StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setSubjectDTOList(SubjectMapperImpl.toDto(student.getSubjects()));
        return studentDTO;
    }
    public static Student toStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setSubjects(SubjectMapperImpl.toSubjects(studentDTO.getSubjectDTOList()));
        return student;
    }
    public static List<StudentDTO> toDTO(List<Student> students) {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student student : students) {
            studentDTOList.add(toDTO(student));
        }
        return studentDTOList;
    }
    public static Student toStudent(StudentCreate studentCreate) {
        Student student = new Student();
        student.setFirstName(studentCreate.getFirstName());
        student.setLastName(studentCreate.getLastName());
        student.setSubjects(SubjectMapperImpl.toSubjects(studentCreate.getSubjectDTOList()));
        return student;
    }

}
