package ru.aston.lessons.springboot.astonhomeworks.mapper;

import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
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
}
