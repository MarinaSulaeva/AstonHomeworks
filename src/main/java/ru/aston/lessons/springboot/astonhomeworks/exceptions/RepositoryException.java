package ru.aston.lessons.springboot.astonhomeworks.exceptions;

public class RepositoryException extends RuntimeException{
    public RepositoryException(Throwable cause) {
        super(cause);
    }
}
