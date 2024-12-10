package ru.aston.lessons.springboot.astonhomeworks.mapper;

import ru.aston.lessons.springboot.astonhomeworks.dto.StudentCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.StudentEntity;
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



    public static List<StudentDTO> toDTOFromEntity(List<StudentEntity> students) {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (StudentEntity student : students) {
            studentDTOList.add(toDTOFromEntity(student));
        }
        return studentDTOList;
    }
    public static StudentEntity toStudentEntity(StudentCreate studentCreate) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName(studentCreate.getFirstName());
        studentEntity.setLastName(studentCreate.getLastName());
        studentEntity.setSubjects(SubjectMapperImpl.toSubjectsEntity(studentCreate.getSubjectDTOList()));
        return studentEntity;
    }
    public static StudentDTO toDTOFromEntity(StudentEntity studentEntity) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setFirstName(studentEntity.getFirstName());
        studentDTO.setLastName(studentEntity.getLastName());
        studentDTO.setSubjectDTOList(SubjectMapperImpl.toDtoFromEntity(studentEntity.getSubjects()));
        return studentDTO;
    }
    public static StudentEntity toStudentEntity(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDTO.getId());
        studentEntity.setFirstName(studentDTO.getFirstName());
        studentEntity.setLastName(studentDTO.getLastName());
        studentEntity.setSubjects(SubjectMapperImpl.toSubjectsEntity(studentDTO.getSubjectDTOList()));
        return studentEntity;
    }

}
