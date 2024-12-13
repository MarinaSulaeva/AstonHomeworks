package ru.aston.lessons.springboot.astonhomeworks.mapper;

import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.TeacherEntity;


import java.util.ArrayList;
import java.util.List;

public class TeacherMapperImpl {


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
