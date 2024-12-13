package ru.aston.lessons.springboot.astonhomeworks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.service.StudentService;
import ru.aston.lessons.springboot.astonhomeworks.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(subjectService.getSubject(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody SubjectCreate subjectCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.saveSubject(subjectCreate));
    }

    @PutMapping
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.ok(subjectService.updateSubject(subjectDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/by_students/{id}")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentsSubjects(id));
    }
}
