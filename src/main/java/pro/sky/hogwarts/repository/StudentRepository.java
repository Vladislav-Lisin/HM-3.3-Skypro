package pro.sky.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.hogwarts.model.Student;



public interface StudentRepository extends JpaRepository<Student, Long> {
}
