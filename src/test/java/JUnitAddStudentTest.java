import domain.Student;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import repository.StudentXMLRepository;
import validation.StudentValidator;


public class JUnitAddStudentTest {

    StudentValidator studentValidator;
    StudentXMLRepository studentXMLRepository;

    @Before
    public void setup() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepository(studentValidator, "studentTest.xml");

    }

    @Test
    public void addStudentTest() {
        Student student = new Student("111", "Vladucu", 222);

        Student addedStudent = studentXMLRepository.save(student);

        assertEquals(addedStudent.getID(), student.getID());
        assertEquals(addedStudent.getNume(), student.getNume());
        assertEquals(addedStudent.getGrupa(), student.getGrupa());

    }

    @Test
    public void addStudentExceptionTest() {
        Student student = new Student("12333", "", 933);
        Student addedStudent = studentXMLRepository.save(student);

        assertNull(addedStudent);

    }

}
