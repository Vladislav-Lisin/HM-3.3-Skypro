

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pro.sky.hogwarts.controller.StudentController;
import pro.sky.hogwarts.model.Student;
import pro.sky.hogwarts.service.StudentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void printParallel_ShouldInvokeServiceMethod() {

        doNothing().when(studentService).printStudentsParallel();


        ResponseEntity<String> response = studentController.printParallel();


        assertEquals("Printing students in parallel mode", response.getBody());
        verify(studentService, times(1)).printStudentsParallel();
    }

    @Test
    void printSynchronized_ShouldInvokeServiceMethod() {

        doNothing().when(studentService).printStudentsSynchronized();

        ResponseEntity<String> response = studentController.printSynchronized();


        assertEquals("Printing students in synchronized mode", response.getBody());
        verify(studentService, times(1)).printStudentsSynchronized();
    }
}