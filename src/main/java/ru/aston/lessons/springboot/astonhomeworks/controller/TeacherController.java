package ru.aston.lessons.springboot.astonhomeworks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherDTO;
import ru.aston.lessons.springboot.astonhomeworks.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
    @Autowired
    private final TeacherService teacherService;

    @GetMapping("{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable int id) {
        return ResponseEntity.ok(teacherService.getTeacher(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getTeachers());
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody TeacherCreate teacherCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.addTeacher(teacherCreate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<TeacherDTO> updateTeacher (@RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok(teacherService.updateTeacher(teacherDTO));
    }
}
