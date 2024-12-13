package ru.aston.lessons.springboot.astonhomeworks.service.impl.jdbc;

import ru.aston.lessons.springboot.astonhomeworks.DAO.StudentDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAO.impl.StudentDAOImpl;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentDTO;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.StudentNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.mapper.StudentMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.model.Student;
import ru.aston.lessons.springboot.astonhomeworks.service.StudentService;


import java.util.List;

public class StudentServiceImpl implements StudentService {


    @Override
    public void addStudent(StudentCreate studentCreate) {
        Student student = StudentMapperImpl.toStudent(studentCreate);
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.createStudent(student);

    }

    @Override
    public List<StudentDTO> getStudents() {
        StudentDAO studentDAO = new StudentDAOImpl();
        return StudentMapperImpl.toDTO(studentDAO.getStudents());

    }

    @Override
    public StudentDTO getStudent(int id) throws StudentNotFoundException {
        StudentDAO studentDAO = new StudentDAOImpl();
        return StudentMapperImpl.toDTO(studentDAO.getStudent(id).orElseThrow(StudentNotFoundException::new));

    }

    @Override
    public void updateStudent(StudentDTO studentDTO) throws StudentNotFoundException {
        existId(studentDTO.getId());
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = StudentMapperImpl.toStudent(studentDTO);
        studentDAO.updateStudent(student);
    }

    @Override
    public void deleteStudent(int id) throws StudentNotFoundException {
        existId(id);
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.deleteStudent(id);
    }

    @Override
    public List<SubjectDTO> getStudentsSubjects(int id) throws StudentNotFoundException {
        return getStudent(id).getSubjectDTOList();
    }

    private void existId(int id) throws StudentNotFoundException {
        StudentDAO studentDAO = new StudentDAOImpl();
        boolean isExist = studentDAO.existId(id);
        if (!isExist) {
            throw new StudentNotFoundException();
        }
    }
}
