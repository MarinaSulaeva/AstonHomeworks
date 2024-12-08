package ru.aston.lessons.springboot.astonhomeworks.mapper;

import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherDTO;
import ru.aston.lessons.springboot.astonhomeworks.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherMapperImpl {
    public static Teacher toTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.getId());
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setSubject(SubjectMapperImpl.toSubject(teacherDTO.getSubjectDTO()));
        return teacher;
    }
    public static TeacherDTO toDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setSubjectDTO(SubjectMapperImpl.toDto(teacher.getSubject()));
        return teacherDTO;
    }
    public static List<TeacherDTO> toDTO(List<Teacher> teachers) {
        List<TeacherDTO> teacherDTOs = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherDTOs.add(toDTO(teacher));
        }
        return teacherDTOs;
    }
    public static Teacher toTeacher(TeacherCreate teacherCreate) {
        Teacher teacher = new Teacher();
        teacher.setSubject(SubjectMapperImpl.toSubject(teacherCreate.getSubjectDTO()));
        teacher.setFirstName(teacherCreate.getFirstName());
        teacher.setLastName(teacherCreate.getLastName());
        return teacher;
    }
}
