import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitIntegrationTest {

    StudentXMLRepository studentXMLRepository;
    TemaXMLRepository temaXMLRepository;
    NotaXMLRepository notaXMLRepository;

    Service service;

    @Before
    public void setup() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti.xml");
        temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
        notaXMLRepository = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);

    }


    @Test
    public void addStudentTest(){
        Student student = new Student("111", "Vladucu", 222);
        Student addedStudent = studentXMLRepository.save(student);

        assertEquals(addedStudent.getID(), student.getID());
        assertEquals(addedStudent.getNume(), student.getNume());
        assertEquals(addedStudent.getGrupa(), student.getGrupa());
    }

    @Test
    public void addAssignmentIntegrationTest() {
        assertEquals(0, service.saveTema("20", "AssignmentTest", 2, 1));
    }

    @Test
    public void addGradeIntegrationTest() {
        service.saveStudent("200", "Georgica Fodorut", 933);
        service.saveTema("10", "o Tema", 7, 4);
        assertEquals(1, service.saveNota("200", "10", 8, 10, "naspa"));
    }

    @Test
    public void addStudentAssignmentIntegrationTest() {
        assertEquals(0, service.saveStudent("201", "Gavrilu Ardei", 933));
        assertEquals(0, service.saveTema("20", "alta tema", 3, 1));
    }

    @Test
    public void addAll() {
        assertEquals(0, service.saveStudent("202", "Dragoiu Vladin", 933));
        assertEquals(0, service.saveTema("21", "alta alta tema", 3, 2));
        assertEquals(0, service.saveNota("202", "21", 5, 2, "sfantu 5"));
    }
}
