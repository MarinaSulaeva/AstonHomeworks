package ru.aston.lessons.springboot.astonhomeworks.exceptions;



public class SubjectNotFoundException extends RuntimeException {

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
