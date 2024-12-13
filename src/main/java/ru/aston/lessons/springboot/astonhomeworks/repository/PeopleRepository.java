package ru.aston.lessons.springboot.astonhomeworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aston.lessons.springboot.astonhomeworks.entity.PeopleEntity;

import java.util.List;
@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, Integer> {
    @Query("SELECT p FROM PeopleEntity p")
    List<PeopleEntity> findAllPeople();
}
