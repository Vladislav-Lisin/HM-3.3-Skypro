package pro.sky.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.hogwarts.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{
    Collection<Faculty>  findByColorIgnoreCase(String color);
    Collection<Faculty> findAllByColorContainingIgnoreCaseOrNameContainingIgnoreCase(String color, String name);

}