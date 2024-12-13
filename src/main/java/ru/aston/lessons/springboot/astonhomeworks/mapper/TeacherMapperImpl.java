package ru.aston.lessons.springboot.astonhomeworks.mapper;

import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.TeacherEntity;
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



    public static TeacherEntity toTeacherEntity(TeacherDTO teacherDTO) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(teacherDTO.getId());
        teacherEntity.setFirstName(teacherDTO.getFirstName());
        teacherEntity.setLastName(teacherDTO.getLastName());
        teacherEntity.setSubject(SubjectMapperImpl.toSubjectEntity(teacherDTO.getSubjectDTO()));
        return teacherEntity;
    }
    public static TeacherDTO toDTOFromEntity(TeacherEntity teacherEntity) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacherEntity.getId());
        teacherDTO.setFirstName(teacherEntity.getFirstName());
        teacherDTO.setLastName(teacherEntity.getLastName());
        teacherDTO.setSubjectDTO(SubjectMapperImpl.toDtoFromEntity(teacherEntity.getSubject()));
        return teacherDTO;
    }
    public static List<TeacherDTO> toDTOFromEntity(List<TeacherEntity> teachers) {
        List<TeacherDTO> teacherDTOs = new ArrayList<>();
        for (TeacherEntity teacher : teachers) {
            teacherDTOs.add(toDTOFromEntity(teacher));
        }
        return teacherDTOs;
    }
    public static TeacherEntity toTeacherEntity(TeacherCreate teacherCreate) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setSubject(SubjectMapperImpl.toSubjectEntity(teacherCreate.getSubjectDTO()));
        teacherEntity.setFirstName(teacherCreate.getFirstName());
        teacherEntity.setLastName(teacherCreate.getLastName());
        return teacherEntity;
    }
}
