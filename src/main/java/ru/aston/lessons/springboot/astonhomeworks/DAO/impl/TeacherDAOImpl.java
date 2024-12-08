package ru.aston.lessons.springboot.astonhomeworks.DAO.impl;

import ru.aston.lessons.springboot.astonhomeworks.CreatingConnection.CreatingConnection;
import ru.aston.lessons.springboot.astonhomeworks.DAO.SubjectDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAO.TeacherDAO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.RepositoryException;
import ru.aston.lessons.springboot.astonhomeworks.model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TeacherDAOImpl implements TeacherDAO {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/aston_jdbc";

    @Override
    public void addTeacher(Teacher teacher) {
        String sql = "INSERT INTO teacher (first_name, last_name, subject_id) VALUES ('" +
                teacher.getFirstName() + "', '" + teacher.getLastName() +"', '" + teacher.getSubject().getId() + "');";
        CreatingConnection.createOrUpdateOrDelete(sql);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        String sql = "UPDATE teacher SET first_name = '" + teacher.getFirstName() + "', last_name = '" + teacher.getLastName()
                + "', subject_id = " + teacher.getSubject().getId() + " WHERE id = " + teacher.getId();
        CreatingConnection.createOrUpdateOrDelete(sql);
    }

    @Override
    public void deleteTeacher(int id) {
        String sql = "DELETE FROM subject WHERE id=" + id;
        CreatingConnection.createOrUpdateOrDelete(sql);
    }

    @Override
    public Optional<Teacher> getTeacher(int id) {
        String sql = "SELECT * FROM teacher WHERE id=" + id;
        Teacher teacher = new Teacher();
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        CreatingConnection.addDriver();
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                teacher.setId(resultSet.getInt("id"));
                teacher.setFirstName(resultSet.getString("first_name"));
                teacher.setLastName(resultSet.getString("last_name"));
                teacher.setSubject(subjectDAO.getSubject(resultSet.getInt("subject_id")).get());
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getCause());
        }
        return Optional.ofNullable(teacher);
    }

    @Override
    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        CreatingConnection.addDriver();
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM teacher")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idTeacher = resultSet.getInt("id");
                String name = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int idSubject = resultSet.getInt("subject_id");
                teachers.add(new Teacher(idTeacher, name, lastName, subjectDAO.getSubject(idSubject).get()));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getCause());
        }
        return teachers;
    }

    @Override
    public boolean existId(int id) {
        String sql = "SELECT exists (SELECT 1 FROM teacher WHERE id = " + id + " LIMIT 1)";
        return CreatingConnection.exitsById(sql);
    }
}
