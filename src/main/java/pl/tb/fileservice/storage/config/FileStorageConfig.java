package pl.tb.fileservice.storage.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.tb.fileservice.storage.config.enumerates.FileGroup;
import pl.tb.fileservice.storage.config.properties.FileGroupProperties;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "storage")
public class FileStorageConfig {

    private Map<FileGroup, FileGroupProperties> fileGroups;

    public FileGroupProperties getFileGroupProperties(FileGroup fileGroup) {
        return fileGroups.get(fileGroup);
    }
}
