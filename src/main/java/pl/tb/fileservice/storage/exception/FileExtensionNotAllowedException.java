package pl.tb.fileservice.storage.exception;

public class FileExtensionNotAllowedException extends RuntimeException {
    public FileExtensionNotAllowedException(String message) {
        super(message);
    }
}
