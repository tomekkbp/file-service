package pl.tb.fileservice.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tb.fileservice.file.exception.FileExtensionByMimeTypeNotFoundException;
import pl.tb.fileservice.file.utils.FileExtension;
import pl.tb.fileservice.file.utils.FileUtils;
import pl.tb.fileservice.storage.config.FileStorageConfig;
import pl.tb.fileservice.storage.config.enumerates.FileGroup;
import pl.tb.fileservice.storage.exception.FileConnotBeMovedException;
import pl.tb.fileservice.storage.exception.FileExtensionNotAllowedException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageService {

    private final FileStorageConfig fileStorageConfig;

    public void storeNewFiles(FileGroup fileGroup) {

        var properties = fileStorageConfig.getFileGroupProperties(fileGroup);
        var sourceFolderPath = properties.getSourceFolderPath();
        var destinationFolderPath = properties.getDestinationFolderPath();
        var errorFolderPath = properties.getErrorFolderPath();
        var allowedFileExtensions = properties.getAllowedFileExtensions();
        var unstoredFiles = getUnstoredFiles(sourceFolderPath.toUri());

        for (File unstoredFile : unstoredFiles) {

            try {

                var mimeType = Files.probeContentType(unstoredFile.toPath());
                var fileExtensionByMimeType = FileExtension.fromMimeType(mimeType);
                var isExtensionAllowed = allowedFileExtensions.stream()
                        .anyMatch(fileExtension -> fileExtension.getMimeType().equals(mimeType));

                if (!isExtensionAllowed) {
                    var errorMessage = String.format("File extension %s not allowed for %s file group", fileExtensionByMimeType, fileGroup);
                    throw new FileExtensionNotAllowedException(errorMessage);
                }

                var unstoredFilePath = unstoredFile.toPath();
                var storedFilePath = prepareStoredFilePath(fileGroup, destinationFolderPath, fileExtensionByMimeType);
                var movedFilePath = Files.move(unstoredFilePath, storedFilePath, StandardCopyOption.REPLACE_EXISTING);

                log.info("File ({}) STORED: {}", unstoredFile.getName(), movedFilePath.toUri());

            } catch (IOException | FileExtensionNotAllowedException | FileExtensionByMimeTypeNotFoundException e) {

                try {

                    var unstoredFilePath = unstoredFile.toPath();
                    var errorFilePath = prepareErrorFilePath(errorFolderPath, unstoredFile);

                    //TODO: check, if there is a file with the same name

                    var movedFilePath = Files.move(unstoredFilePath, errorFilePath, StandardCopyOption.REPLACE_EXISTING);

                    log.error("File ({}) ERROR: {}", unstoredFile.getName(), movedFilePath.toUri(), e);

                } catch (IOException ex) {
                    throw new FileConnotBeMovedException(ex);
                }
            }
        }
    }

    private Path prepareStoredFilePath(FileGroup fileGroup, Path destinationFolderPath, FileExtension fileExtensionByMimeType) {
        return Path.of(destinationFolderPath + "/" + FileUtils.prepareFileStorageName(fileGroup, fileExtensionByMimeType));
    }

    private Path prepareErrorFilePath(Path errorFolderPath, File file) {
        return Path.of(errorFolderPath + "/" + file.getName());
    }

    private List<File> getUnstoredFiles(URI sourceFolderPath) {

        File sourceFolder = new File(sourceFolderPath);

        var exists = sourceFolder.exists();
        var isDirectory = sourceFolder.isDirectory();

        if (!exists) {

            throw new RuntimeException("Path not exists");
        }

        if (!isDirectory) {

            throw new RuntimeException("Not a directory");
        }

        File[] newFiles = sourceFolder.listFiles();

        if (Objects.isNull(newFiles)) {

            return List.of();

        } else {

            return List.of(newFiles);
        }
    }
}
