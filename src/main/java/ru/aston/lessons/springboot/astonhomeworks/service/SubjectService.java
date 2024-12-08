package ru.aston.lessons.springboot.astonhomeworks.service;

import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.SubjectNotFoundException;

import java.util.List;

public interface SubjectService {
    SubjectDTO getSubject(int id) throws SubjectNotFoundException;
    List<SubjectDTO> getAllSubjects();
    void saveSubject(SubjectCreate subjectCreate);
    void deleteSubject(int id) throws SubjectNotFoundException;
    void updateSubject(SubjectDTO subjectDTO) throws SubjectNotFoundException;

}
