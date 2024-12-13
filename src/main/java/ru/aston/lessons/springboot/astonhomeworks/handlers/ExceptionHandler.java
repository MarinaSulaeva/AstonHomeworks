package ru.aston.lessons.springboot.astonhomeworks.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.StudentNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.SubjectNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.TeacherNotFoundException;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFound(StudentNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = SubjectNotFoundException.class)
    public ResponseEntity<?> handleSubjectNotFound(SubjectNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = TeacherNotFoundException.class)
    public ResponseEntity<?> handleTeacherNotFound(TeacherNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
