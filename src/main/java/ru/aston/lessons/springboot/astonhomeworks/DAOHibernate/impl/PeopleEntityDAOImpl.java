package ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import ru.aston.lessons.springboot.astonhomeworks.CreatingConnection.CreatingEntityManager;
import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.PeopleEntityDAO;
import ru.aston.lessons.springboot.astonhomeworks.entity.PeopleEntity;


import java.util.List;

public class PeopleEntityDAOImpl implements PeopleEntityDAO {
    private final EntityManagerFactory entityManagerFactory = CreatingEntityManager.getEntityManagerFactory();

    @Override
    public List<PeopleEntity> getAllPeopleInSchool() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String jpqlQuery = "SELECT s FROM PeopleEntity s";
        TypedQuery<PeopleEntity> query = entityManager.createQuery(jpqlQuery, PeopleEntity.class);
        List<PeopleEntity> people = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return people;
    }
}
