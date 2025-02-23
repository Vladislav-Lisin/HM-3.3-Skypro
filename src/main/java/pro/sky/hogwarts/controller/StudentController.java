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
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        Collection<Student> result = studentService.getStudentsByAgeBetween(min, max);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> update(@PathVariable("id") Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.update(id, student);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
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

    @GetMapping("/count")
    public int getStudentCount() {
        return studentService.getStudentCount();
    }

    @GetMapping("/avg-age")
    public int getStudentAvgAge() {
        return studentService.getStudentAvgAge();
    }

    @GetMapping("/last5")
    public Collection<Student> getLast5Students() {
        return studentService.getLast5Student();
    }

    @GetMapping("/list/letter/a")
    public Collection<Student> findByNameIsStartingWithA() {
        return studentService.findByNameIsStartingWithA();
    }
    @GetMapping("/average-age")
    public double getAverageAgeByStream() {
        return studentService.getAverageAge();
    }
    @GetMapping("/summary")
    public int getSummary() {
        return studentService.getSum();
    }

}