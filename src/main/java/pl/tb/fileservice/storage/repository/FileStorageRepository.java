package pl.tb.fileservice.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tb.fileservice.storage.repository.entity.FileStorageEntity;

import java.util.UUID;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorageEntity, UUID> {
}