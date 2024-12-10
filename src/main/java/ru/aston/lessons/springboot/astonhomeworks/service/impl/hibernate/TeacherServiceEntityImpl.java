package ru.aston.lessons.springboot.astonhomeworks.service.impl.hibernate;

import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.TeacherEntityDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.impl.TeacherEntityDAOImpl;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.TeacherEntity;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.TeacherNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.mapper.TeacherMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.service.TeacherService;

import java.util.List;

public class TeacherServiceEntityImpl implements TeacherService {
    @Override
    public void addTeacher(TeacherCreate teacherCreate) {
        TeacherEntityDAO teacherEntityDAO = new TeacherEntityDAOImpl();
        teacherEntityDAO.addTeacher(TeacherMapperImpl.toTeacherEntity(teacherCreate));
    }

    @Override
    public void deleteTeacher(int id) throws TeacherNotFoundException {
        TeacherEntityDAO teacherEntityDAO = new TeacherEntityDAOImpl();
        TeacherEntity teacherEntity = teacherEntityDAO.getTeacher(id).orElseThrow(TeacherNotFoundException::new);
        teacherEntityDAO.deleteTeacher(id);
    }

    @Override
    public void updateTeacher(TeacherDTO teacherDTO) throws TeacherNotFoundException {
        TeacherEntityDAO teacherEntityDAO = new TeacherEntityDAOImpl();
        TeacherEntity teacherEntity = teacherEntityDAO.getTeacher(teacherDTO.getId()).orElseThrow(TeacherNotFoundException::new);
        teacherEntityDAO.updateTeacher(TeacherMapperImpl.toTeacherEntity(teacherDTO));
    }

    @Override
    public TeacherDTO getTeacher(int id) throws TeacherNotFoundException {
        TeacherEntityDAO teacherEntityDAO = new TeacherEntityDAOImpl();
        TeacherEntity teacherEntity = teacherEntityDAO.getTeacher(id).orElseThrow(TeacherNotFoundException::new);
        return TeacherMapperImpl.toDTOFromEntity(teacherEntity);
    }

    @Override
    public List<TeacherDTO> getTeachers() {
        TeacherEntityDAO teacherEntityDAO = new TeacherEntityDAOImpl();
        List<TeacherEntity> teacherDTOS = teacherEntityDAO.getTeachers();
        return TeacherMapperImpl.toDTOFromEntity(teacherDTOS);
    }
}
