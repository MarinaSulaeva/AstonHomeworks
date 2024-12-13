package ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import ru.aston.lessons.springboot.astonhomeworks.CreatingConnection.CreatingEntityManager;
import ru.aston.lessons.springboot.astonhomeworks.DAOHibernate.StudentEntityDAO;
import ru.aston.lessons.springboot.astonhomeworks.entity.StudentEntity;
import ru.aston.lessons.springboot.astonhomeworks.entity.SubjectEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentEntityDAOImpl implements StudentEntityDAO {
    private final EntityManagerFactory entityManagerFactory = CreatingEntityManager.getEntityManagerFactory();
    @Override
    public void createStudent(StudentEntity student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<SubjectEntity> subjects = student.getSubjects();
        List<SubjectEntity> managedSubjects = new ArrayList<SubjectEntity>();
        for (SubjectEntity subject : subjects) {
            SubjectEntity managedSubject = entityManager.find(SubjectEntity.class, subject.getId());
            managedSubjects.add(managedSubject);
        }
        student.setSubjects(managedSubjects);
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateStudent(StudentEntity student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        StudentEntity studentEntity= entityManager.find(StudentEntity.class, student.getId());
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        studentEntity.setSubjects(student.getSubjects());
        entityManager.merge(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Optional<StudentEntity> getStudent(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        StudentEntity studentEntity= entityManager.find(StudentEntity.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(studentEntity);
    }


    @Override
    public void deleteStudent(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        StudentEntity studentEntity= entityManager.find(StudentEntity.class, id);
        entityManager.remove(studentEntity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public List<StudentEntity> getStudents() {
        return getStudentsWithSolvingNPlusOneProblem();
    }

    @Override
    public List<SubjectEntity> getStudentsSubjects(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //для получения lazyexception
//        StudentEntity student = entityManager.find(StudentEntity.class, id);
        StudentEntity student = entityManager.find(StudentEntity.class, id,
                Collections.singletonMap("javax.persistence.loadgraph", entityManager.createEntityGraph(StudentEntity.class).addSubgraph("subjects")));
        entityManager.close();
        return student.getSubjects();
    }

    private List<StudentEntity> getStudentsWithNPlusOneProblem() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String jpqlQuery = "SELECT s FROM StudentEntity s";
        TypedQuery<StudentEntity> query = entityManager.createQuery(jpqlQuery, StudentEntity.class);
        List<StudentEntity> students = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return students;
    }

    private List<StudentEntity> getStudentsWithSolvingNPlusOneProblem() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String jpqlQuery = "SELECT s FROM StudentEntity s JOIN FETCH s.subjects";
        TypedQuery<StudentEntity> query = entityManager.createQuery(jpqlQuery, StudentEntity.class);
        List<StudentEntity> students = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return students;
    }



}
