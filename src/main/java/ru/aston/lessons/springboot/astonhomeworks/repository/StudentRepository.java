package ru.aston.lessons.springboot.astonhomeworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aston.lessons.springboot.astonhomeworks.entity.StudentEntity;
import ru.aston.lessons.springboot.astonhomeworks.entity.SubjectEntity;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    @Override
    List<StudentEntity> findAll();

    Optional<StudentEntity> findById(Integer id);

//    @Query("SELECT subjects FROM StudentEntity students JOIN SubjectEntity on WHERE id=?1")
//    List<SubjectEntity> getSubjectsByStudentEntity(StudentEntity student);

    @Override
    boolean existsById(Integer integer);
}
