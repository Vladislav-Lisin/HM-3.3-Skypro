package pro.sky.hogwarts.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.hogwarts.model.Faculty;
import pro.sky.hogwarts.model.Student;
import pro.sky.hogwarts.service.FacultyService;

import java.util.Collection;


@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public Faculty readFaculty(@PathVariable long id) {
        return facultyService.readFaculty(id);
    }

    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        return facultyService.updateFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<Collection<Faculty>> filterByColor(@PathVariable String color) {
        return facultyService.getByColor(color);
    }

    @GetMapping("/colorOrName/{colorOrName}")
    public ResponseEntity<Collection<Faculty>> filterByColorOrName(@PathVariable String colorOrName) {
        return facultyService.getByColorOrName(colorOrName);
    }



    @GetMapping("/{id}/students")
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@PathVariable long id) {
        Collection<Student> result = facultyService.getFacultyStudents(id);
        if (result.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}