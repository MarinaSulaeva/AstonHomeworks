package ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import ru.aston.lessons.springboot.astonhomeworks.CreatingConnection.CreatingEntityManager;
import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.TeacherEntityDAO;
import ru.aston.lessons.springboot.astonhomeworks.entity.SubjectEntity;
import ru.aston.lessons.springboot.astonhomeworks.entity.TeacherEntity;


import java.util.List;
import java.util.Optional;

public class TeacherEntityDAOImpl implements TeacherEntityDAO {
    private final EntityManagerFactory entityManagerFactory = CreatingEntityManager.getEntityManagerFactory();
    @Override
    public void addTeacher(TeacherEntity teacher) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        SubjectEntity managedSubject = entityManager.find(SubjectEntity.class, teacher.getSubject().getId());
        teacher.setSubject(managedSubject);
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();

        entityManager.close();

    }

    @Override
    public void updateTeacher(TeacherEntity teacher) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TeacherEntity teacherEntity = entityManager.find(TeacherEntity.class, teacher.getId());
        teacherEntity.setFirstName(teacher.getFirstName());
        teacherEntity.setLastName(teacher.getLastName());
        teacherEntity.setSubject(teacher.getSubject());
        entityManager.merge(teacherEntity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void deleteTeacher(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TeacherEntity teacherEntity = entityManager.find(TeacherEntity.class, id);
        entityManager.remove(teacherEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Optional<TeacherEntity> getTeacher(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TeacherEntity teacherEntity= entityManager.find(TeacherEntity.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(teacherEntity);
    }

    @Override
    public List<TeacherEntity> getTeachers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String jpqlQuery = "SELECT s FROM TeacherEntity s";
        TypedQuery<TeacherEntity> query = entityManager.createQuery(jpqlQuery, TeacherEntity.class);
        List<TeacherEntity> teachers = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return teachers;
    }

}
