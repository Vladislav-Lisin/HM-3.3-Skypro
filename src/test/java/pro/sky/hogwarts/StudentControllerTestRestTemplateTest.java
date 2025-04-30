package pro.sky.hogwarts.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pro.sky.hogwarts.model.Faculty;
import pro.sky.hogwarts.model.Student;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTestRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetStudentById() {
        ResponseEntity<Student> response = restTemplate.getForEntity("/students/1", Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testAddStudent() {
        Student student = new Student();
        student.setName("Test Student");
        student.setAge(20);

        ResponseEntity<Student> response = restTemplate.postForEntity("/students", student, Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Student", response.getBody().getName());
    }

    @Test
    void testFindByAgeBetween() {
        ResponseEntity<Collection> response = restTemplate.getForEntity(
                "/students/age?min=18&max=25", Collection.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student();
        student.setName("Updated Student");
        student.setAge(21);

        ResponseEntity<Student> response = restTemplate.exchange(
                "/students/1", HttpMethod.PUT, new HttpEntity<>(student), Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testDeleteStudent() {
        ResponseEntity<Void> response = restTemplate.exchange(
                "/students/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetByAge() {
        ResponseEntity<Collection> response = restTemplate.getForEntity(
                "/students?age=20", Collection.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testFacultyByStudent() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity(
                "/students/faculty/1", Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetStudentCount() {
        ResponseEntity<Integer> response = restTemplate.getForEntity(
                "/students/count", Integer.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetStudentAvgAge() {
        ResponseEntity<Integer> response = restTemplate.getForEntity(
                "/students/avg-age", Integer.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetLast5Students() {
        ResponseEntity<Collection> response = restTemplate.getForEntity(
                "/students/last5", Collection.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}