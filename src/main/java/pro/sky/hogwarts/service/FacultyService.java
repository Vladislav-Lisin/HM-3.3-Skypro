package pro.sky.hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pro.sky.hogwarts.model.Faculty;
import pro.sky.hogwarts.model.Student;
import pro.sky.hogwarts.repository.FacultyRepository;
import pro.sky.hogwarts.repository.StudentRepository;

import java.util.Collection;


@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Был использован метод createFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(long id) {
        logger.info("Был использован метод readFaculty");
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Был использован метод updateFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty deleteFaculty(long id) {
        logger.info("Был использован метод deleteFaculty");
        Faculty faculty = readFaculty(id);
        if (faculty != null) {
            facultyRepository.deleteById(id);
        }
        return faculty;
    }

    public ResponseEntity<Collection<Faculty>> getByColor(String color) {
        logger.info("Был использован метод getByColor");
        Collection<Faculty> result = facultyRepository.findByColorIgnoreCase(color);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Collection<Faculty>> getByColorOrName(String colorOrName) {
        logger.info("Был использован метод getByColorOrName");
        Collection<Faculty> result = facultyRepository.findAllByColorContainingIgnoreCaseOrNameContainingIgnoreCase(colorOrName, colorOrName);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


    public Collection<Student> getFacultyStudents(long id) {
        logger.info("Был использован метод getFacultyStudents");
        return studentRepository.findAllByFaculty_id(id);
    }
}