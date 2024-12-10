package ru.aston.lessons.springboot.astonhomeworks.DAO.impl;

import ru.aston.lessons.springboot.astonhomeworks.CreatingConnection.CreatingConnection;
import ru.aston.lessons.springboot.astonhomeworks.DAO.StudentToSubjectDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAO.SubjectDAO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.RepositoryException;
import ru.aston.lessons.springboot.astonhomeworks.model.StudentToSubject;
import ru.aston.lessons.springboot.astonhomeworks.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentToSubjectDAOImpl implements StudentToSubjectDAO {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/aston_jdbc";

    @Override
    public void save(StudentToSubject studentToSubject) {
        String sql = "INSERT INTO student_subject (student_id, subject_id) VALUES ('" + studentToSubject.getStudentId() + "', '" +
                studentToSubject.getSubjectId() + "')";
        CreatingConnection.createOrUpdateOrDelete(sql);
    }

    @Override
    public void update(StudentToSubject studentToSubject) {
        String sql = "UPDATE student_subject SET student_id = '" + studentToSubject.getStudentId() + "', subject_id = '" +
                studentToSubject.getSubjectId() + "' WHERE subject_id = " + studentToSubject.getId();
        CreatingConnection.createOrUpdateOrDelete(sql);
    }


    @Override
    public List<Subject> findByStudent(int idStudent) {
        String sql = "SELECT subject_id * FROM student_subject WHERE student_id=" + idStudent;

        List<Subject> subjects = new ArrayList<>();
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        CreatingConnection.addDriver();
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subjects.add(subjectDAO.getSubject(resultSet.getInt("subject_id")).get());
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getCause());
        }
        return subjects;
    }

    @Override
    public void deleteByStudent(int idStudent) {
        String sql = "DELETE FROM student_subject WHERE student_id=" + idStudent;
        CreatingConnection.createOrUpdateOrDelete(sql);
    }

    @Override
    public void deleteBySubject(int idSubject) {
        String sql = "DELETE FROM student_subject WHERE subject_id=" + idSubject;
        CreatingConnection.createOrUpdateOrDelete(sql);
    }


}
