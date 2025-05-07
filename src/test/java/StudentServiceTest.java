

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.hogwarts.model.Student;
import pro.sky.hogwarts.repository.StudentRepository;
import pro.sky.hogwarts.service.StudentService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void printStudentsParallel_ShouldPrintStudents() {
        // Arrange
        List<Student> students = Arrays.asList(
                new Student(1L, "Harry", 17),
                new Student(2L, "Hermione", 17),
                new Student(3L, "Ron", 17),
                new Student(4L, "Draco", 17)
        );
        when(studentRepository.findAll()).thenReturn(students);

        // Act
        studentService.printStudentsParallel();

        // Assert
        verify(studentRepository, times(1)).findAll();
        // Здесь мы не можем проверить вывод в консоль, но можем убедиться, что метод был вызван
    }

    @Test
    void printStudentsSynchronized_ShouldPrintStudentsSynchronized() {
        // Arrange
        List<Student> students = Arrays.asList(
                new Student(1L, "Harry", 17),
                new Student(2L, "Hermione", 17),
                new Student(3L, "Ron", 17),
                new Student(4L, "Draco", 17)
        );
        when(studentRepository.findAll()).thenReturn(students);

        // Act
        studentService.printStudentsSynchronized();

        // Assert
        verify(studentRepository, times(1)).findAll();
        // Проверяем, что метод был вызван
    }
}