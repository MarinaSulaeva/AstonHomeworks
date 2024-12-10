package ru.aston.lessons.springboot.astonhomeworks.DAO.impl;

import ru.aston.lessons.springboot.astonhomeworks.CreatingConnection.CreatingConnection;
import ru.aston.lessons.springboot.astonhomeworks.DAO.StudentDAO;
import ru.aston.lessons.springboot.astonhomeworks.DAO.StudentToSubjectDAO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.RepositoryException;
import ru.aston.lessons.springboot.astonhomeworks.model.Student;
import ru.aston.lessons.springboot.astonhomeworks.model.StudentToSubject;
import ru.aston.lessons.springboot.astonhomeworks.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAOImpl implements StudentDAO {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/aston_jdbc";
    @Override
    public void createStudent(Student student) {
        String sql = "INSERT INTO student (first_name, last_name) VALUES ('" + student.getFirstName() + "', '" + student.getLastName() + "')";
        Student returnStudent = new Student();
        CreatingConnection.addDriver();
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.executeUpdate();
            ResultSet resultSet =  statement.getGeneratedKeys();
            while (resultSet.next()) {
                int idStudent = resultSet.getInt("id");
                returnStudent.setId(idStudent);
                returnStudent.setFirstName(resultSet.getString("first_name"));
                returnStudent.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getCause());
        }
        StudentToSubjectDAO studentToSubjectDAO = new StudentToSubjectDAOImpl();
        for (Subject subject : student.getSubjects()) {
            studentToSubjectDAO.save(new StudentToSubject(0, returnStudent.getId(), subject.getId()));
        }

    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE student SET first_name = '" + student.getFirstName() + "', last_name = '" + student.getLastName() + "' WHERE student_id = " + student.getId();
        Student returnStudent = new Student();
        CreatingConnection.addDriver();
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.executeUpdate();
            ResultSet resultSet =  statement.getGeneratedKeys();
            while (resultSet.next()) {
                int idStudent = resultSet.getInt("id");
                returnStudent.setId(idStudent);
                returnStudent.setFirstName(resultSet.getString("first_name"));
                returnStudent.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getCause());
        }
        StudentToSubjectDAO studentToSubjectDAO = new StudentToSubjectDAOImpl();

        for (Subject subject : student.getSubjects()) {
            studentToSubjectDAO.save(new StudentToSubject(0, returnStudent.getId(), subject.getId()));
        }

    }

    @Override
    public Optional<Student> getStudent(int id) {
        String sql = "SELECT * FROM student WHERE id=" + id;
        Student student = new Student();
        StudentToSubjectDAO studentToSubjectDAO = new StudentToSubjectDAOImpl();
        CreatingConnection.addDriver();
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idStudent = resultSet.getInt("id");
                String name = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                student.setId(idStudent);
                student.setFirstName(name);
                student.setLastName(lastName);
                student.setSubjects(studentToSubjectDAO.findByStudent(idStudent));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getCause());
        }

        return Optional.ofNullable(student);
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id=" + id;
        CreatingConnection.createOrUpdateOrDelete(sql);
        StudentToSubjectDAO studentToSubjectDAO = new StudentToSubjectDAOImpl();
        studentToSubjectDAO.deleteByStudent(id);

    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        StudentToSubjectDAO studentToSubjectDAO = new StudentToSubjectDAOImpl();
        CreatingConnection.addDriver();
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM student")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idStudent = resultSet.getInt("id");
                String name = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                students.add(new Student(idStudent, name, lastName, studentToSubjectDAO.findByStudent(idStudent)));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getCause());
        }
            return students;
    }

    @Override
    public boolean existId(int id) {
        String sql = "SELECT exists (SELECT 1 FROM student WHERE id = " + id + " LIMIT 1)";
        return CreatingConnection.exitsById(sql);
    }
}
