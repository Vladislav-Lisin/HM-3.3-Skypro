package pro.sky.hogwarts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pro.sky.hogwarts.model.Faculty;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTestRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Test Faculty");
        faculty.setColor("Blue");

        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculty", faculty, Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Faculty", response.getBody().getName());
    }

    @Test
    void testReadFaculty() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/1", Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Updated Faculty");
        faculty.setColor("Red");

        ResponseEntity<Faculty> response = restTemplate.exchange(
                "/faculty", org.springframework.http.HttpMethod.PUT, new HttpEntity<>(faculty), Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Faculty", response.getBody().getName());
    }

    @Test
    void testDeleteFaculty() {
        ResponseEntity<Faculty> response = restTemplate.exchange(
                "/faculty/1", org.springframework.http.HttpMethod.DELETE, null, Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testFilterByColor() {
        ResponseEntity<Collection> response = restTemplate.getForEntity(
                "/faculty/color/Blue", Collection.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testFilterByColorOrName() {
        ResponseEntity<Collection> response = restTemplate.getForEntity(
                "/faculty/colorOrName/Blue", Collection.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetStudentsByFaculty() {
        ResponseEntity<Collection> response = restTemplate.getForEntity(
                "/faculty/1/students", Collection.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}