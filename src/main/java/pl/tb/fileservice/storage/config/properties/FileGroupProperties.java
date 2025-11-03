package pl.tb.fileservice.storage.config.properties;

import lombok.Getter;
import lombok.Setter;
import pl.tb.fileservice.file.utils.FileExtension;

import java.nio.file.Path;
import java.util.List;

@Getter
@Setter
public class FileGroupProperties {
    private Path sourceFolderPath;
    private Path destinationFolderPath;
    private Path errorFolderPath;
    private List<FileExtension> allowedFileExtensions;
}
