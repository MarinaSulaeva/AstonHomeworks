package ru.aston.lessons.springboot.astonhomeworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.lessons.springboot.astonhomeworks.entity.SubjectEntity;

import java.util.List;
import java.util.Optional;
@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
    @Override
    List<SubjectEntity> findAll();

    Optional<SubjectEntity> findById(Integer id);

    @Override
    boolean existsById(Integer integer);
}
