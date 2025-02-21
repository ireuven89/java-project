import com.example.school.entities.TaskEntity;
import com.example.school.model.migration.MigrationStatusEnum;
import com.example.school.services.migration.MigrationService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class TestMigrationService {

    private String queueName = "queue";
    @Mock
    MongoClient mongoClient;
    @Mock
    MongoTemplate mongoTemplate;
    @InjectMocks
    MigrationService migrationService;
    @Test
    public void test(){
        Query query = new Query();
        Pageable pageable = PageRequest.of(0, 1000);
        query.addCriteria(Criteria.where("status").is(MigrationStatusEnum.PENDING.getValue()));  // Replace "status" with the field you are querying on
        query.with(pageable);

        when(mongoTemplate.find(query, TaskEntity.class, queueName))
                .thenReturn(new ArrayList<>());
        migrationService.processTasks("migration", queueName);
    }
}
