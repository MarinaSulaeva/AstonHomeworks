package ru.aston.lessons.springboot.astonhomeworks.service.impl.hibernate;

import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.SubjectEntityDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.impl.SubjectEntityDAOImpl;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.SubjectEntity;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.SubjectNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.mapper.SubjectMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.service.SubjectService;

import java.util.List;

public class SubjectServiceEntityImpl implements SubjectService {
    @Override
    public SubjectDTO getSubject(int id) throws SubjectNotFoundException {
        SubjectEntityDAO subjectEntityDAO = new SubjectEntityDAOImpl();
        return SubjectMapperImpl.toDtoFromEntity(subjectEntityDAO.getSubject(id).orElseThrow(SubjectNotFoundException::new));
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        SubjectEntityDAO subjectEntityDAO = new SubjectEntityDAOImpl();
        List<SubjectEntity> subjectEntities = subjectEntityDAO.getSubjects();
        return SubjectMapperImpl.toDtoFromEntity(subjectEntities);
    }

    @Override
    public void saveSubject(SubjectCreate subjectCreate) {
        SubjectEntityDAO subjectEntityDAO = new SubjectEntityDAOImpl();
        subjectEntityDAO.createSubject(SubjectMapperImpl.toSubjectEntity(subjectCreate));
    }

    @Override
    public void deleteSubject(int id) throws SubjectNotFoundException {
        SubjectEntityDAO subjectEntityDAO = new SubjectEntityDAOImpl();
        SubjectEntity subjectEntity = subjectEntityDAO.getSubject(id).orElseThrow(SubjectNotFoundException::new);
        subjectEntityDAO.deleteSubject(id);

    }

    @Override
    public void updateSubject(SubjectDTO subjectDTO) throws SubjectNotFoundException {
        SubjectEntityDAO subjectEntityDAO = new SubjectEntityDAOImpl();
        SubjectEntity subjectEntity = subjectEntityDAO.getSubject(subjectDTO.getId()).orElseThrow(SubjectNotFoundException::new);
        subjectEntityDAO.updateSubject(SubjectMapperImpl.toSubjectEntity(subjectDTO));
    }
}
