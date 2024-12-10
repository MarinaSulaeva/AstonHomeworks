package ru.aston.lessons.springboot.astonhomeworks.CreatingConnection;

import ru.aston.lessons.springboot.astonhomeworks.exceptions.RepositoryException;
import lombok.Data;

import java.sql.*;
@Data
public class CreatingConnection {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/aston_jdbc";
    private static final String DRIVER_CLASS_KEY = "org.postgresql.Driver";


    public static void createOrUpdateOrDelete(String sql) {
        addDriver();
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            System.out.println("зашли в секцию try");
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getCause());
        }
    }

    public static void addDriver() {
        try {
            Class.forName(DRIVER_CLASS_KEY);
        } catch (ClassNotFoundException e) {
            throw new RepositoryException(e.getCause());
        }
    }

    public static boolean exitsById(String sql) {
        boolean isExists = false;
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isExists = resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return isExists;
    }


}
