import com.example.school.repositories.mongo.ElementsRepository;
import com.example.school.services.ElementDto;
import com.example.school.services.python.PythonGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class PythonGeneratorTest {

    @Mock
    private ElementsRepository elementsRepository;

    @InjectMocks
    private PythonGenerator generator;

    @Test
    public void testSave() {
        ElementDto element = new ElementDto();
        element.setName("Test Element");

        generator.save(element);

        verify(elementsRepository, Mockito.times(1)).save(Mockito.any());

    }
}
