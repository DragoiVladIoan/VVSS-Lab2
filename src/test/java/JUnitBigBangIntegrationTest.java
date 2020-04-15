import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JUnitBigBangIntegrationTest {

    private StudentValidator studentValidator;
    private StudentXMLRepository studentXMLRepository;

    private TemaValidator temaValidator;
    private TemaXMLRepository temaXMLRepository;

    private NotaValidator notaValidator;
    private NotaXMLRepository notaXMLRepository;


    @Before
    public void setup() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepository(studentValidator, "studentTest.xml");

        temaValidator = new TemaValidator();
        temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");

        notaValidator = new NotaValidator();
        notaXMLRepository = new NotaXMLRepository(notaValidator, "note.xml");
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
    public void addAssignmentTest() {
        Tema tema = new Tema("10", "Tema noua", 12, 10);
        Tema temaNoua = temaXMLRepository.save(tema);

        assertEquals(temaNoua.getID(), tema.getID());
        assertEquals(temaNoua.getDescriere(), tema.getDescriere());
        assertEquals(temaNoua.getDeadline(), tema.getDeadline());
        assertEquals(temaNoua.getStartline(), tema.getStartline());

    }

    @Test
    public void addGradeTest() {
        Nota nota = new Nota(new Pair<>("111", "10"), 3.14, 12, "Very very poor job");
        Nota notaNoua = notaXMLRepository.save(nota);

        assertEquals(notaNoua.getID(), nota.getID());
        assertEquals(notaNoua.getNota(), nota.getNota());
        assertEquals(notaNoua.getSaptamanaPredare(), nota.getSaptamanaPredare());
        assertEquals(notaNoua.getFeedback(), nota.getFeedback());

    }

    @Test
    public void testAll() {
        addStudentTest();
        addAssignmentTest();
        addGradeTest();
    }
}
