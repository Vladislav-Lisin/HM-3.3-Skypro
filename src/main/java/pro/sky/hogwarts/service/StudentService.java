package pro.sky.hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pro.sky.hogwarts.model.Faculty;
import pro.sky.hogwarts.model.Student;
import pro.sky.hogwarts.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public StudentService(StudentRepository studentRepository) {
        logger.info("Был использован метод StudentService");
        this.studentRepository = studentRepository;
    }

    public Collection<Student> getStudentsByAgeBetween(int min, int max) {
        logger.info("Был использован метод getStudentsByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }


    public Student add(Student student) {
        logger.info("Был использован метод add");
        return studentRepository.save(student);
    }

    public Faculty getStudentFaculty(long id) {
        logger.info("Был использован метод getStudentFaculty");
        if (studentRepository.findById(id).isPresent()) {
            return studentRepository.findById(id).get().getFaculty();
        }
        return null;
    }

    public Student get(Long id) {
        logger.info("Был использован метод get");
        return studentRepository.findById(id).orElse(null);
    }


    public Student update(Long id, Student student) {
        logger.info("Был использован метод update");
        student.setId(id);
        return studentRepository.save(student);
    }


    public ResponseEntity<Void> deleteStudent(long id) {
        logger.info("Был использован метод deleteStudent");
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Student> getByAge(int age) {
        logger.info("Был использован метод getByAge");
        return studentRepository.findAll()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }

    public int getStudentCount() {
        logger.info("Был использован метод getStudentCount");
        return studentRepository.getStudentCount();
    }

    public int getStudentAvgAge() {
        logger.info("Был использован метод getStudentAvgAge");
        return studentRepository.getStudentAvgAge();
    }

    public Collection<Student> getLast5Student() {
        logger.info("Был использован метод getLast5Student");
        return studentRepository.getLast5Students();
    }

    public Collection<Student> findByNameIsStartingWithA() {
        logger.info("Был использован метод findByNameIsStartingWithA");
        return studentRepository.findAll().stream()
                .filter(student -> student.getName()
                        .startsWith("A"))
                .toList();
    }


    public double getAverageAge() {
        logger.info("Был использован метод getAverageAge");
        return studentRepository.findAll().stream().mapToInt(Student::getAge).average().getAsDouble();
    }

    public int getSum() {
        logger.info("Был использован метод getSum");
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);


    }

}