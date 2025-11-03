package pl.tb.fileservice.file.exception;

public class FileExtensionByMimeTypeNotFoundException extends RuntimeException {
    public FileExtensionByMimeTypeNotFoundException(String message) {
        super(message);
    }
}
