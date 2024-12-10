package ru.aston.lessons.springboot.astonhomeworks.service.impl;


import ru.aston.lessons.springboot.astonhomeworks.DAO.SubjectDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAO.impl.SubjectDAOImpl;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.SubjectNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.mapper.SubjectMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.model.Subject;
import ru.aston.lessons.springboot.astonhomeworks.service.SubjectService;


import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    @Override
    public SubjectDTO getSubject(int id) throws SubjectNotFoundException {
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        return SubjectMapperImpl.toDto(subjectDAO.getSubject(id).orElseThrow(SubjectNotFoundException::new));
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        return SubjectMapperImpl.toDto(subjectDAO.getSubjects());
    }

    @Override
    public void saveSubject(SubjectCreate subjectCreate) {
        Subject subject = SubjectMapperImpl.toSubject(subjectCreate);
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        subjectDAO.createSubject(subject);
    }

    @Override
    public void deleteSubject(int id) throws SubjectNotFoundException {
        existId(id);
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        subjectDAO.deleteSubject(id);
    }

    @Override
    public void updateSubject(SubjectDTO subjectDTO) throws SubjectNotFoundException {
        existId(subjectDTO.getId());
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        System.out.println("зашли в сервис");
        subjectDAO.updateSubject(SubjectMapperImpl.toSubject(subjectDTO));
    }

    private void existId(int id) throws SubjectNotFoundException {
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        boolean isExist = subjectDAO.existId(id);
        if (!isExist) {
            throw new SubjectNotFoundException();
        }
    }
}
