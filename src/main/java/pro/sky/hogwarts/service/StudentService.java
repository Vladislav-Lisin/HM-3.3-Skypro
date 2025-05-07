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
    private static final Object flag = new Object();

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
                .reduce(0, Integer::sum);
    }

    public void studentThread() {
        logger.info("Был использован метод studentThread");
        List<Student> studentList = studentRepository.findAll();
        System.out.println("************************");

        System.out.println(studentList.get(0));
        System.out.println(studentList.get(1));

        new Thread(() -> {
            System.out.println(studentList.get(2));
            System.out.println(studentList.get(3));
        }).start();
        new Thread(() -> {
            System.out.println(studentList.get(4));
            System.out.println(studentList.get(5));
        }).start();
    }


    public void synchronizedStudentThread() throws InterruptedException {
        logger.info("Был использован метод synchronizedStudentThread");
        List<Student> studentList = studentRepository.findAll();
        printStudent(studentList.get(0));
        printStudent(studentList.get(1));

        var t1 = new Thread(() -> {
            printStudent(studentList.get(2));
            printStudent(studentList.get(3));
        });
        var t2 = new Thread(() -> {
            printStudent(studentList.get(4));
            printStudent(studentList.get(5));
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }

    private static void printStudent(Student student){
        synchronized (flag) {
            System.out.println(student);
        }
    }


    public List<Student> getAllStudents() {
        logger.info("Был использован метод getAllStudents");
        return studentRepository.findAll();
    }


    public void printStudentsSynchronized() {
        logger.info("Был использован метод printStudentsSynchronized");
        List<Student> students = getAllStudents();

        Thread thread1 = new Thread(() -> {
            printStudent(students.get(0));
            printStudent(students.get(1));
        });

        Thread thread2 = new Thread(() -> {
            printStudent(students.get(2));
            printStudent(students.get(3));
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread was interrupted", e);
        }
    }


    public void printStudentsParallel() {
        logger.info("Был использован метод printStudentsParallel");
        List<Student> students = getAllStudents();

        new Thread(() -> {
            System.out.println(students.get(0));
            System.out.println(students.get(1));
        }).start();

        new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        }).start();
    }
}