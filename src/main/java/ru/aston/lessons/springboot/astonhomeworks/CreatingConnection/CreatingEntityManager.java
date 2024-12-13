package ru.aston.lessons.springboot.astonhomeworks.CreatingConnection;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

public class CreatingEntityManager {
    @Getter
    private static EntityManagerFactory entityManagerFactory = create();

    private static EntityManagerFactory create() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        return entityManagerFactory;
    }


}
