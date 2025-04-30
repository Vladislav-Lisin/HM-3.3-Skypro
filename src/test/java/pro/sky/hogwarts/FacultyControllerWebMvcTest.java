package pro.sky.hogwarts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.hogwarts.controller.FacultyController;
import pro.sky.hogwarts.model.Faculty;
import pro.sky.hogwarts.model.Student;
import pro.sky.hogwarts.service.FacultyService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    void createFaculty_shouldReturnFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Gryffindor");
        faculty.setColor("Red");

        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Gryffindor\",\"color\":\"Red\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void readFaculty_shouldReturnFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Gryffindor");

        when(facultyService.readFaculty(anyLong())).thenReturn(faculty);

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Gryffindor"));
    }

    @Test
    void updateFaculty_shouldReturnUpdatedFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Updated Gryffindor");

        when(facultyService.updateFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(put("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Updated Gryffindor\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Gryffindor"));
    }

    @Test
    void deleteFaculty_shouldReturnDeletedFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Gryffindor");

        when(facultyService.deleteFaculty(anyLong())).thenReturn(faculty);

        mockMvc.perform(delete("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Gryffindor"));
    }

    @Test
    void filterByColor_shouldReturnFaculties() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setColor("Red");

        when(facultyService.getByColor(anyString()))
                .thenReturn(ResponseEntity.ok(Collections.singletonList(faculty)));

        mockMvc.perform(get("/faculty/color/Red"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].color").value("Red"));
    }

    @Test
    void filterByColorOrName_shouldReturnFaculties() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");

        when(facultyService.getByColorOrName(anyString()))
                .thenReturn(ResponseEntity.ok(Collections.singletonList(faculty)));

        mockMvc.perform(get("/faculty/colorOrName/Gryffindor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gryffindor"));
    }

    @Test
    void getStudentsByFaculty_shouldReturnStudents() throws Exception {
        Student student = new Student();
        student.setName("Harry Potter");

        when(facultyService.getFacultyStudents(anyLong()))
                .thenReturn(Collections.singletonList(student));

        mockMvc.perform(get("/faculty/1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Harry Potter"));
    }
}