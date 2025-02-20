package com.example.school.services.migration;
import com.example.school.entities.MigrationEntity;
import com.example.school.handler.ResourceNotFoundException;
import com.example.school.model.migration.MigrationDto;
import com.example.school.model.migration.MigrationStatusDto;
import com.example.school.model.migration.MigrationStatusEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class MigrationManager {

    @Autowired
    private MigrationService migrationService;
    private final ModelMapper modelMapper = new ModelMapper();


    @Async
    public void startMigration(String migrationName) {
        MigrationEntity entity = migrationService.getMigrationEntity(migrationName);

        migrationService.updateMigration(migrationName, MigrationStatusEnum.IN_PROGRESS);

        migrationService.processTasks(entity.getMigrationName(), entity.getQueueName());
    }

    public void createMigration(MigrationDto migrationDto) {
        MigrationEntity entity = new MigrationEntity();

        entity.setMigrationName(migrationDto.getMigrationName());
        entity.setQueueName(migrationDto.getQueueName());
        entity.setStatus(MigrationStatusEnum.PENDING.getValue());
        entity.setTotalTasksCount(0);
        entity.setTotalTasksCompleted(0);
        entity.setTimeLeft(0);
        entity.setTotalTasksLeft(0);

       migrationService.migrationsMongoTemplate.save(entity);
    }

    public MigrationStatusDto getMigrationStatus(String name) throws Exception {
         MigrationEntity migrationEntity = migrationService.getMigrationEntity(name);

        if (migrationEntity.getId() == null) {
            throw new ResourceNotFoundException("migration not found");
        }

        MigrationStatusDto migrationDto = new MigrationStatusDto();
        modelMapper.map(migrationEntity, migrationDto);

        return migrationDto;
    }

    public void stopMigration(String name) throws Exception {
        MigrationEntity migrationEntity = migrationService.getMigrationEntity(name);

        if (migrationEntity.getId() == null) {
            throw new ResourceNotFoundException("migration not found", name);
        }

        migrationService.updateMigration(name, MigrationStatusEnum.STOPPED);
    }
}
