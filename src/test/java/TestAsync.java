import com.example.school.SchoolApplication;
import com.example.school.config.AsyncConfig;
import com.example.school.services.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = AsyncConfig.class)
public class TestAsync {

    @Autowired
    private TaskService taskService;


    @Test
    public void test(){
        taskService.taskExecutor();
    }
}
