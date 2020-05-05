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
import static org.junit.jupiter.api.Assertions.assertNull;


public class JUnitAddAssignmentTest {

    TemaXMLRepository temaXMLRepository;
    Service service;

    @Before
    public void setup() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti.xml");
        temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void addAssignmentWithMissingId()
    {
        Tema assignment = new Tema("", "desc", 5, 3);
        int resultSave = service.saveTema(assignment.getID(), assignment.getDescriere(), assignment.getDeadline(), assignment.getStartline());
        Assert.assertEquals(resultSave, 1);
    }

    @Test
    public void addAssignmentWithMissingDescription()
    {
        Tema assignment = new Tema("tema", "", 10, 3);
        int resultSave = service.saveTema(assignment.getID(), assignment.getDescriere(), assignment.getDeadline(), assignment.getStartline());
        Assert.assertEquals(resultSave, 1);
    }

    @Test
    public void addAssignmentWithWrongDeadline()
    {
        Tema assignment = new Tema("tema", "description", 2020, 2);
        int resultSave = service.saveTema(assignment.getID(), assignment.getDescriere(), assignment.getDeadline(), assignment.getStartline());
        Assert.assertEquals(resultSave, 1);
    }

    @Test
    public void addAssignmentWithWrongStartline()
    {
        Tema assignment = new Tema("tema2", "description", 4, -5);
        int resultSave = service.saveTema(assignment.getID(), assignment.getDescriere(), assignment.getDeadline(), assignment.getStartline());
        Assert.assertEquals(resultSave, 1);
    }

    @Test
    public void addCorrectAssignmentTest() {
        Tema assignment = new Tema("tema", "description", 4, 1);
        int resultSave = service.saveTema(assignment.getID(), assignment.getDescriere(), assignment.getDeadline(), assignment.getStartline());
        Assert.assertEquals(resultSave, 0);
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
    public void addAssignmentExceptionTest() {
        Tema tema = new Tema("", "", 500, -10);
        Tema temaNoua = temaXMLRepository.save(tema);

        assertNull(temaNoua);
    }
}