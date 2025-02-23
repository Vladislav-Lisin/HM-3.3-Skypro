package pro.sky.hogwarts.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pro.sky.hogwarts.model.Faculty;
import pro.sky.hogwarts.model.Student;
import pro.sky.hogwarts.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Collection<Student> getStudentsByAgeBetween(int min, int max){
        return studentRepository.findByAgeBetween(min, max);
    }


    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Faculty getStudentFaculty(long id) {
        if (studentRepository.findById(id).isPresent()) {
            return studentRepository.findById(id).get().getFaculty();
        }
        return null;
    }

    public Student get(Long id) {
        return studentRepository.findById(id ).orElse(null);
    }


    public Student update(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }


    public ResponseEntity<Void> deleteStudent(long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Student> getByAge(int age) {
        return studentRepository.findAll()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }

    public int getStudentCount() {
        return studentRepository.getStudentCount();
    }

    public int getStudentAvgAge() {
        return studentRepository.getStudentAvgAge();
    }

    public Collection<Student> getLast5Student() {
        return studentRepository.getLast5Students();
    }
}