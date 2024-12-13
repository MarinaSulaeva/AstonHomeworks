package ru.aston.lessons.springboot.astonhomeworks.service.impl.spring;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentDTO;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.StudentEntity;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.StudentNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.mapper.StudentMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.mapper.SubjectMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.repository.StudentRepository;
import ru.aston.lessons.springboot.astonhomeworks.service.StudentService;


import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    @Transactional
    public StudentDTO addStudent(StudentCreate studentCreate) {
        StudentEntity studentEntity = studentRepository.save(StudentMapperImpl.toStudentEntity(studentCreate));
        return StudentMapperImpl.toDTOFromEntity(studentRepository.save(studentEntity));
    }

    @Override
    public List<StudentDTO> getStudents() {
        return StudentMapperImpl.toDTOFromEntity(studentRepository.findAll());
    }

    @Override
    public StudentDTO getStudent(int id) {
        return StudentMapperImpl.toDTOFromEntity(studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("студент с таким id не найден")));
    }

    @Override
    @Transactional
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        checkStudentExists(studentDTO.getId());
        StudentEntity studentEntity = studentRepository.save(StudentMapperImpl.toStudentEntity(studentDTO));
        return StudentMapperImpl.toDTOFromEntity(studentEntity);
    }

    @Override
    public void deleteStudent(int id) {
        checkStudentExists(id);
        studentRepository.deleteById(id);
    }


    @Override
    @Transactional
    public List<SubjectDTO> getStudentsSubjects(int id) {
        //без аннотации transactional получаем LIE
        StudentEntity studentEntity = studentRepository.
                findById(id).orElseThrow(()-> new StudentNotFoundException("студент с таким id не найден"));
        return SubjectMapperImpl.toDtoFromEntity(studentEntity.getSubjects());
    }

    private void checkStudentExists(int id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("студент с таким id не найден");
        }
    }
}
