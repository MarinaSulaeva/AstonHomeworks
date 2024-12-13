package ru.aston.lessons.springboot.astonhomeworks.mapper;

import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.SubjectEntity;
import ru.aston.lessons.springboot.astonhomeworks.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectMapperImpl {
    public static SubjectDTO toDto(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setDescription(subject.getDescription());
        dto.setSubjectName(subject.getSubjectName());
        return dto;
    }
    public static Subject toSubject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setId(subjectDTO.getId());
        subject.setSubjectName(subjectDTO.getSubjectName());
        subject.setDescription(subjectDTO.getDescription());
        return subject;
    }
    public static List<SubjectDTO> toDto(List<Subject> subjects) {
        List<SubjectDTO> dtos = new ArrayList<>();
        for (Subject subject : subjects) {
            SubjectDTO dto = toDto(subject);
            dtos.add(dto);
        }
        return dtos;
    }
    public static List<Subject> toSubjects(List<SubjectDTO> subjectDTOs) {
        List<Subject> subjectList = new ArrayList<>();
        for (SubjectDTO subjectDTO : subjectDTOs) {
            Subject subject = toSubject(subjectDTO);
            subjectList.add(subject);
        }
        return subjectList;
    }
    public static Subject toSubject(SubjectCreate subjectCreate) {
        Subject subject = new Subject();
        subject.setDescription(subjectCreate.getDescription());
        subject.setSubjectName(subjectCreate.getSubjectName());
        return subject;
    }


    public static SubjectDTO toDtoFromEntity(SubjectEntity subjectEntity) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subjectEntity.getId());
        dto.setDescription(subjectEntity.getDescription());
        dto.setSubjectName(subjectEntity.getSubjectName());
        return dto;
    }
    public static SubjectEntity toSubjectEntity(SubjectDTO subjectDTO) {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(subjectDTO.getId());
        subjectEntity.setSubjectName(subjectDTO.getSubjectName());
        subjectEntity.setDescription(subjectDTO.getDescription());
        return subjectEntity;
    }
    public static List<SubjectDTO> toDtoFromEntity(List<SubjectEntity> subjects) {
        List<SubjectDTO> dtos = new ArrayList<>();
        for (SubjectEntity subject : subjects) {
            SubjectDTO dto = toDtoFromEntity(subject);
            dtos.add(dto);
        }
        return dtos;
    }
    public static List<SubjectEntity> toSubjectsEntity(List<SubjectDTO> subjectDTOs) {
        List<SubjectEntity> subjectList = new ArrayList<>();
        for (SubjectDTO subjectDTO : subjectDTOs) {
            SubjectEntity subjectEntity = toSubjectEntity(subjectDTO);
            subjectList.add(subjectEntity);
        }
        return subjectList;
    }
    public static SubjectEntity toSubjectEntity(SubjectCreate subjectCreate) {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setDescription(subjectCreate.getDescription());
        subjectEntity.setSubjectName(subjectCreate.getSubjectName());
        return subjectEntity;
    }
}
