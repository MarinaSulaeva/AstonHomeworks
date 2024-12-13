package ru.aston.lessons.springboot.astonhomeworks.exceptions;



public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
