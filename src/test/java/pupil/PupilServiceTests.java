package pupil;

import com.example.school.resources.PupilResource;
import com.example.school.services.PupilService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class PupilServiceTests {


    @Test(expected = ClassNotFoundException.class)
    public void testGetPupil() throws Exception {
        PupilService service = Mockito.mock(PupilService.class);
        when(service.getPupil(0L)).thenReturn(null);
        PupilResource resource = service.getPupil(0L);

       Assert.assertNull(resource);
    }

    @Test
    public void testCreatePupil() {
        PupilResource resource = new PupilResource(1.0, 2.0, new ArrayList<>(), new ArrayList<>());
        Long expected = 1L;
        PupilService service = Mockito.mock(PupilService.class);
        when(service.createPupil(resource)).thenReturn(expected);

        Long id = service.createPupil(resource);

        assertEquals(expected, id);
    }

    @Test(expected = Exception.class)
    public void testUpdatePupil() throws Exception{
        PupilResource resource = new PupilResource(1.0, 2.0, new ArrayList<>(), new ArrayList<>());
        Long id = 0L;
        PupilService service = Mockito.mock(PupilService.class);

        service.updatePupil(id, resource);
    }

    public boolean testDeletePupil(int target, List<Integer> arr){
        Set<Integer> setInt = new HashSet<>();

        for(int i = 0; i < arr.size(); i++){
            if(setInt.contains(target - arr.get(i))){
                return true;
            }else {
                setInt.add(arr.get(i));
            }
        }

        return false;
    }

}
