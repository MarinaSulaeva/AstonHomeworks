package ru.aston.lessons.springboot.astonhomeworks.service.impl.spring;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.TeacherEntity;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.TeacherNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.mapper.TeacherMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.repository.TeacherRepository;
import ru.aston.lessons.springboot.astonhomeworks.service.TeacherService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public TeacherDTO addTeacher(TeacherCreate teacherCreate) {
        TeacherEntity teacherEntity = TeacherMapperImpl.toTeacherEntity(teacherCreate);
        return TeacherMapperImpl.toDTOFromEntity(teacherRepository.save(teacherEntity));
    }

    @Override
    public void deleteTeacher(int id) {
        checkExistTeacher(id);
        teacherRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TeacherDTO updateTeacher(TeacherDTO teacherDTO) {
        checkExistTeacher(teacherDTO.getId());
        TeacherEntity teacherEntity = TeacherMapperImpl.toTeacherEntity(teacherDTO);
        return TeacherMapperImpl.toDTOFromEntity(teacherRepository.save(teacherEntity));
    }

    @Override
    public TeacherDTO getTeacher(int id) {
        return TeacherMapperImpl.toDTOFromEntity(teacherRepository.findById(id).orElseThrow(()->new TeacherNotFoundException("учитель с таким id не найден")));
    }

    @Override
    public List<TeacherDTO> getTeachers() {
        return TeacherMapperImpl.toDTOFromEntity(teacherRepository.findAll());
    }

    private void checkExistTeacher(Integer id) {
        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException("учитель с таким id не найден");
        }
    }
}
