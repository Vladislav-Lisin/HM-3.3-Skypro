package pro.sky.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.hogwarts.model.Student;

import java.util.Collection;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int minAge, int maxAge);
    Collection<Student> findAllByFaculty_id(long id);
}
