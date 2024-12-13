package ru.aston.lessons.springboot.astonhomeworks.service.impl.jdbc;

import ru.aston.lessons.springboot.astonhomeworks.DAO.TeacherDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAO.impl.TeacherDAOImpl;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherDTO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.TeacherNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.mapper.TeacherMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.service.TeacherService;


import java.util.List;

public class TeacherServiceImpl implements TeacherService {


    @Override
    public void addTeacher(TeacherCreate teacherCreate) {
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        teacherDAO.addTeacher(TeacherMapperImpl.toTeacher(teacherCreate));
    }

    @Override
    public void deleteTeacher(int id) throws TeacherNotFoundException {
        existId(id);
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        teacherDAO.deleteTeacher(id);
    }

    @Override
    public void updateTeacher(TeacherDTO teacherDTO) throws TeacherNotFoundException {
        existId(teacherDTO.getId());
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        teacherDAO.updateTeacher(TeacherMapperImpl.toTeacher(teacherDTO));
    }

    @Override
    public TeacherDTO getTeacher(int id) throws TeacherNotFoundException {

        TeacherDAO teacherDAO = new TeacherDAOImpl();
        return TeacherMapperImpl.toDTO(teacherDAO.getTeacher(id).orElseThrow(TeacherNotFoundException::new));
    }

    @Override
    public List<TeacherDTO> getTeachers() {
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        return TeacherMapperImpl.toDTO(teacherDAO.getTeachers());
    }

    private void existId(int id) throws TeacherNotFoundException {
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        boolean isExist = teacherDAO.existId(id);
        if (!isExist) {
            throw new TeacherNotFoundException();
        }
    }
}
