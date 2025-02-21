package pro.sky.hogwarts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.hogwarts.model.Faculty;
import pro.sky.hogwarts.model.Student;
import pro.sky.hogwarts.service.StudentService;


import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Student get(@PathVariable("id") Long id) {
        return studentService.get(id);
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @GetMapping("/age")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam int min, @RequestParam int max){
        Collection<Student> result = studentService.getStudentsByAgeBetween(min, max);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public Student update(@PathVariable("id") Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

    @GetMapping
    public List<Student> getByAge(@RequestParam int age) {
        return studentService.getByAge(age);
    }

    @GetMapping("/faculty/{id}")
    public ResponseEntity<Faculty> facultyByStudent(@PathVariable long id) {
        Faculty foundFaculty = studentService.getStudentFaculty(id);
        if (foundFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
}