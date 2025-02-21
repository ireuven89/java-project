package com.example.school.services.migration;
import com.example.school.entities.MigrationEntity;
import com.example.school.handler.ResourceNotFoundException;
import com.example.school.model.migration.MigrationDto;
import com.example.school.model.migration.MigrationStatusDto;
import com.example.school.model.migration.MigrationStatusEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MigrationManager {

    @Autowired
    private MigrationService migrationService;
    private final ModelMapper modelMapper = new ModelMapper();


    public void startMigration(String migrationName) throws Exception{
        MigrationEntity entity = migrationService.getMigrationEntity(migrationName);

        if(entity == null){
            throw new ResourceNotFoundException("Migration not found", migrationName);
        }

        migrationService.updateMigration(migrationName, "status", MigrationStatusEnum.IN_PROGRESS.getValue());

        migrationService.processTasks(entity.getMigrationName(), entity.getQueueName());
    }

    public void createMigration(MigrationDto migrationDto) {

       migrationService.createMigration(migrationDto);
    }

    public MigrationStatusDto getMigrationStatus(String name) throws Exception {
         MigrationEntity migrationEntity = migrationService.getMigrationEntity(name);

        if (migrationEntity == null) {
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

        migrationService.updateMigration(name, "status", MigrationStatusEnum.STOPPED.getValue());
    }
}
