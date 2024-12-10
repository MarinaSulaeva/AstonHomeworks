package ru.aston.lessons.springboot.astonhomeworks.DAO.impl;


import ru.aston.lessons.springboot.astonhomeworks.CreatingConnection.CreatingConnection;
import ru.aston.lessons.springboot.astonhomeworks.DAO.StudentToSubjectDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAO.SubjectDAO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.RepositoryException;
import ru.aston.lessons.springboot.astonhomeworks.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectDAOImpl implements SubjectDAO {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/aston_jdbc";
    @Override
    public void createSubject(Subject subject) {
        String sql = "INSERT INTO subject (subject_name, description) VALUES ('" +
                subject.getSubjectName() + "', '" + subject.getDescription() + "');";
        CreatingConnection.createOrUpdateOrDelete(sql);
    }

    @Override
    public void updateSubject(Subject subject) {
        System.out.println("зашли в DAO");
        String sql = "UPDATE subject SET subject_name = '" + subject.getSubjectName() + "', description = '" +
                subject.getDescription() + "' WHERE id = " + subject.getId();
        System.out.println(sql);
        CreatingConnection.createOrUpdateOrDelete(sql);
    }

    @Override
    public void deleteSubject(int id) {
        String sql = "DELETE FROM subject WHERE id=" + id;
        CreatingConnection.createOrUpdateOrDelete(sql);
        StudentToSubjectDAO studentToSubjectDAO = new StudentToSubjectDAOImpl();
        studentToSubjectDAO.deleteBySubject(id);
    }

    @Override
    public Optional<Subject> getSubject(int id) {
        String sql = "SELECT * FROM subject WHERE id=" + id;
        Subject subject = new Subject();

        CreatingConnection.addDriver();
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idSubject = resultSet.getInt("id");
                String name = resultSet.getString("subject_name");
                String description = resultSet.getString("description");
                subject.setId(idSubject);
                subject.setSubjectName(name);
                subject.setDescription(description);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getCause());
        }

        return Optional.ofNullable(subject);
    }

    @Override
    public List<Subject> getSubjects() {
        List<Subject> subjects = new ArrayList<>();
        CreatingConnection.addDriver();
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM subject")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idSubject = resultSet.getInt("id");
                String name = resultSet.getString("subject_name");
                String description = resultSet.getString("description");
                subjects.add(new Subject(idSubject, name, description));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getCause());
        }
        return subjects;
    }

    @Override
    public boolean existId(int id) {
        String sql = "SELECT exists (SELECT 1 FROM subject WHERE id = " + id + " LIMIT 1)";
        return CreatingConnection.exitsById(sql);
    }
}
