import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.TemaXMLRepository;
import validation.TemaValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class JUnitAddAssignmentTest {

    TemaXMLRepository temaXMLRepository;
    TemaValidator temaValidator;

    @Before
    public void setup() {
        temaValidator = new TemaValidator();
        temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
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
        Tema tema = new Tema("", "Tema veche", 12, 10);
        Tema temaNoua = temaXMLRepository.save(tema);

        assertNull(temaNoua);
    }
    
}