package ru.aston.lessons.springboot.astonhomeworks.exceptions;


public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }
}
