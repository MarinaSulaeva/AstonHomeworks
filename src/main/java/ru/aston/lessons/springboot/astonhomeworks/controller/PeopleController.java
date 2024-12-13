package ru.aston.lessons.springboot.astonhomeworks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.lessons.springboot.astonhomeworks.dto.PeopleDTO;
import ru.aston.lessons.springboot.astonhomeworks.service.PeopleService;

import java.util.List;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;

    @GetMapping("/all")
    public ResponseEntity<List<PeopleDTO>> getAllPeople() {
        return ResponseEntity.ok(peopleService.getAllPeopleAtSchool());
    }
}
