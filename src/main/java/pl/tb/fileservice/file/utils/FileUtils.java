package pl.tb.fileservice.file.utils;

import pl.tb.fileservice.storage.config.enumerates.FileGroup;

import java.util.UUID;

public class FileUtils {

    public static String prepareFileStorageName(FileGroup fileGroup, FileExtension fileExtension) {
        return String.format("%s-%s.%s", fileGroup.name(), UUID.randomUUID(), fileExtension.getExtensionType());
    }
}
