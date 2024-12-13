package ru.aston.lessons.springboot.astonhomeworks.service.impl.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.entity.SubjectEntity;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.SubjectNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.mapper.SubjectMapperImpl;
import ru.aston.lessons.springboot.astonhomeworks.repository.SubjectRepository;
import ru.aston.lessons.springboot.astonhomeworks.service.SubjectService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public SubjectDTO getSubject(int id) {
        return SubjectMapperImpl.toDtoFromEntity(subjectRepository.findById(id).
                orElseThrow(()-> new SubjectNotFoundException("предмет с таким id не найден")));
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        return SubjectMapperImpl.toDtoFromEntity(subjectRepository.findAll());
    }

    @Override
    public SubjectDTO saveSubject(SubjectCreate subjectCreate) {
        SubjectEntity subjectEntity = SubjectMapperImpl.toSubjectEntity(subjectCreate);
        return SubjectMapperImpl.toDtoFromEntity(subjectRepository.save(subjectEntity));
    }

    @Override
    public void deleteSubject(int id) {
        checkExistSubject(id);
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectDTO updateSubject(SubjectDTO subjectDTO) {
        checkExistSubject(subjectDTO.getId());
        SubjectEntity subjectEntity = SubjectMapperImpl.toSubjectEntity(subjectDTO);
        return SubjectMapperImpl.toDtoFromEntity(subjectRepository.save(subjectEntity));
    }

    private void checkExistSubject(int id) {
        if (!subjectRepository.existsById(id)) {
            throw new SubjectNotFoundException("предмет с таким id не найден");
        }
    }
}
