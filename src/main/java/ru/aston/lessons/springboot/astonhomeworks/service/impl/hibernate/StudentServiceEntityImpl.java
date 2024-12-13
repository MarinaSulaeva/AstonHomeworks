package ru.aston.lessons.springboot.astonhomeworks.service.impl.hibernate;

import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.StudentEntityDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.impl.StudentEntityDAOImpl;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentDTO;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.StudentEntity;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.StudentNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.mapper.StudentMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.mapper.SubjectMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.service.StudentService;

import java.util.List;

public class StudentServiceEntityImpl implements StudentService {
    @Override
    public void addStudent(StudentCreate studentCreate) {
        StudentEntityDAO studentEntityDAO = new StudentEntityDAOImpl();
        studentEntityDAO.createStudent(StudentMapperImpl.toStudentEntity(studentCreate));

    }

    @Override
    public List<StudentDTO> getStudents() {
        StudentEntityDAO studentEntityDAO = new StudentEntityDAOImpl();
        List<StudentEntity> studentEntities = studentEntityDAO.getStudents();
        return StudentMapperImpl.toDTOFromEntity(studentEntities);
    }

    @Override
    public StudentDTO getStudent(int id) throws StudentNotFoundException {
        StudentEntityDAO studentEntityDAO = new StudentEntityDAOImpl();
        return StudentMapperImpl.toDTOFromEntity(studentEntityDAO.getStudent(id).orElseThrow(StudentNotFoundException::new));
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) throws StudentNotFoundException {
        StudentEntityDAO studentEntityDAO = new StudentEntityDAOImpl();
//        для выброса исключения
        StudentEntity studentEntity = studentEntityDAO.getStudent(studentDTO.getId()).orElseThrow(StudentNotFoundException::new);
        studentEntityDAO.updateStudent(StudentMapperImpl.toStudentEntity(studentDTO));
    }

    @Override
    public void deleteStudent(int id) throws StudentNotFoundException {
        StudentEntityDAO studentEntityDAO = new StudentEntityDAOImpl();
        //        для выброса исключения
        StudentEntity studentEntity = studentEntityDAO.getStudent(id).orElseThrow(StudentNotFoundException::new);
        studentEntityDAO.deleteStudent(id);
    }

    @Override
    public List<SubjectDTO> getStudentsSubjects(int id) throws StudentNotFoundException{
        StudentEntityDAO studentEntityDAO = new StudentEntityDAOImpl();
        StudentEntity studentEntity = studentEntityDAO.getStudent(id).orElseThrow(StudentNotFoundException::new);
        return SubjectMapperImpl.toDtoFromEntity(studentEntityDAO.getStudentsSubjects(id));
    }
}
