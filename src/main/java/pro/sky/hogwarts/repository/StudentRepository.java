package pro.sky.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.hogwarts.model.Student;

import java.util.Collection;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int minAge, int maxAge);
    Collection<Student> findAllByFaculty_id(long id);

    @Query(value = "select count(*) from student",nativeQuery = true)
    int getStudentCount();

    @Query(value ="select avg(age) from student" , nativeQuery = true)
    int getStudentAvgAge();

    @Query(value = "select * from student order by id desc limit 5",nativeQuery = true)
    Collection<Student> getLast5Students();

    Object findByAge(Integer any);
}
