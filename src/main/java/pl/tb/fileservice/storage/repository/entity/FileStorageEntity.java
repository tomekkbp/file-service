package pl.tb.fileservice.storage.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pl.tb.fileservice.storage.config.enumerates.FileGroup;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "filestorage", name = "stored_files")
@Getter
@Setter
@NoArgsConstructor
public class FileStorageEntity {

    @Id
    @Column(name = "stored_file_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "file_group", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private FileGroup fileGroup;

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(name = "stored_name", nullable = false, unique = true)
    private String storedName;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Column(name = "file_extension", nullable = false)
    private String fileExtension;

    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @Column(name = "create_user", nullable = false)
    private String createUser;

    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "deleted", nullable = false)
    private Boolean isDeleted;
}