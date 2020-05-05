import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;


public class JUnitAddStudentTest {

    StudentXMLRepository studentXMLRepository;
    Service service;

    @Before
    public void setup() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);

    }


    @Test
    public void addStudentWithEmptyId(){
        Student student = new Student("", "Gavrilas Andrei", 200);
        int result = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        Assert.assertEquals(result, 1);
    }

    @Test
    public void addStudentWithMissingId(){
        Student student = new Student(null, "Gavrilas Andrei", 200);
        int result = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        Assert.assertEquals(result, 1);
    }

    @Test
    public void addStudentWithEmptyName(){
        Student student = new Student("10", null, 200);
        int result = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        Assert.assertEquals(result, 1);
    }

    @Test
    public void addStudentWithMissingName(){
        Student student = new Student("10", null, 200);
        int result = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        Assert.assertEquals(result, 1);
    }

    @Test
    public void addStudentWithLowerGroup(){
        Student student = new Student("10", "Vlad Dragoi", 109);
        int result = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        Assert.assertEquals(result, 1);
    }

    @Test
    public void addStudentWithHigherGroup(){
        Student student = new Student("10", "Vlad Dragoi", 939);
        int result = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        Assert.assertEquals(result, 1);
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
