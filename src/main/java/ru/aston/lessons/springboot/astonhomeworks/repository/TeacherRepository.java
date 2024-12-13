package ru.aston.lessons.springboot.astonhomeworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.lessons.springboot.astonhomeworks.entity.TeacherEntity;

import java.util.List;
import java.util.Optional;
@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {
    @Override
    List<TeacherEntity> findAll();

    Optional<TeacherEntity> findById(Integer id);

    boolean existsById(Integer id);
}
