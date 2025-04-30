package pro.sky.hogwarts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.hogwarts.controller.StudentController;
import pro.sky.hogwarts.model.Faculty;
import pro.sky.hogwarts.model.Student;
import pro.sky.hogwarts.service.StudentService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudent_shouldReturnStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Harry Potter");

        when(studentService.get(anyLong())).thenReturn(student);

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Harry Potter"));
    }

    @Test
    void addStudent_shouldReturnAddedStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Harry Potter");

        when(studentService.add(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Harry Potter\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Harry Potter"));
    }

    @Test
    void findByAgeBetween_shouldReturnStudents() throws Exception {
        Student student = new Student();
        student.setAge(15);

        when(studentService.getStudentsByAgeBetween(anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(student));

        mockMvc.perform(get("/students/age?min=10&max=20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(15));
    }

    @Test
    void updateStudent_shouldReturnUpdatedStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Updated Harry");

        when(studentService.update(anyLong(), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Harry\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Harry"));
    }

    @Test
    void deleteStudent_shouldReturnOk() throws Exception {
        when(studentService.deleteStudent(anyLong())).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getByAge_shouldReturnStudents() throws Exception {
        Student student = new Student();
        student.setAge(15);

        when(studentService.getByAge(anyInt())).thenReturn(Collections.singletonList(student));

        mockMvc.perform(get("/students?age=15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(15));
    }

    @Test
    void facultyByStudent_shouldReturnFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");

        when(studentService.getStudentFaculty(anyLong())).thenReturn(faculty);

        mockMvc.perform(get("/students/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gryffindor"));
    }

    @Test
    void getStudentCount_shouldReturnCount() throws Exception {
        when(studentService.getStudentCount()).thenReturn(5);

        mockMvc.perform(get("/students/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    void getStudentAvgAge_shouldReturnAvgAge() throws Exception {
        when(studentService.getStudentAvgAge()).thenReturn(14);

        mockMvc.perform(get("/students/avg-age"))
                .andExpect(status().isOk())
                .andExpect(content().string("14"));
    }

    @Test
    void getLast5Students_shouldReturnStudents() throws Exception {
        Student student1 = new Student();
        student1.setName("Student1");
        Student student2 = new Student();
        student2.setName("Student2");

        when(studentService.getLast5Student()).thenReturn(Arrays.asList(student1, student2));

        mockMvc.perform(get("/students/last5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Student1"))
                .andExpect(jsonPath("$[1].name").value("Student2"));
    }
}