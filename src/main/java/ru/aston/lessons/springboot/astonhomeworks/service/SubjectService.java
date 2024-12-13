package ru.aston.lessons.springboot.astonhomeworks.service;

import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    SubjectDTO getSubject(int id);
    List<SubjectDTO> getAllSubjects();
    SubjectDTO saveSubject(SubjectCreate subjectCreate);
    void deleteSubject(int id);
    SubjectDTO updateSubject(SubjectDTO subjectDTO);

}
