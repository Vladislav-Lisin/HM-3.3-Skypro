package pro.sky.hogwarts.service;

import org.springframework.stereotype.Service;
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


    public Student add(Student student) {
        return studentRepository.save(student);
    }


    public Student get(Long id) {
        return studentRepository.findById(id ).orElse(null);
    }


    public Student update(Long id, Student student) {
        return studentRepository .save(student);
    }


    public void delete(Long id) {
        studentRepository.deleteById(id);    }

    public List<Student> getByAge(int age) {
        return studentRepository.findAll()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }
}