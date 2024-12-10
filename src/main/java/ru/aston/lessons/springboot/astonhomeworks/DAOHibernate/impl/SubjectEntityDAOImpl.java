package ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import ru.aston.lessons.springboot.astonhomeworks.CreatingConnection.CreatingEntityManager;
import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.SubjectEntityDAO;
import ru.aston.lessons.springboot.astonhomeworks.entity.SubjectEntity;

import java.util.List;
import java.util.Optional;

public class SubjectEntityDAOImpl implements SubjectEntityDAO {
    private final EntityManagerFactory entityManagerFactory = CreatingEntityManager.getEntityManagerFactory();
    @Override
    public void createSubject(SubjectEntity subject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(subject);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void updateSubject(SubjectEntity subject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        SubjectEntity subjectEntity= entityManager.find(SubjectEntity.class, subject.getId());
        subjectEntity.setSubjectName(subject.getSubjectName());
        subjectEntity.setDescription(subject.getDescription());
        entityManager.merge(subjectEntity);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void deleteSubject(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        SubjectEntity subjectEntity= entityManager.find(SubjectEntity.class, id);

        entityManager.remove(subjectEntity);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public Optional<SubjectEntity> getSubject(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        SubjectEntity subjectEntity= entityManager.find(SubjectEntity.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(subjectEntity);
    }

    @Override
    public List<SubjectEntity> getSubjects() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String jpqlQuery = "SELECT s FROM SubjectEntity s";
        TypedQuery<SubjectEntity> query = entityManager.createQuery(jpqlQuery, SubjectEntity.class);
        List<SubjectEntity> subjects = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return subjects;
    }


}
