package pl.tb.fileservice.file.utils;

import pl.tb.fileservice.file.exception.FileExtensionByMimeTypeNotFoundException;

public enum FileExtension {

    //DOCUMENTS
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx"),

    //IMAGES
    JPG("image/jpeg", "jpg"),
    JPEG("image/jpeg", "jpeg"),
    PNG("", "png");

    private final String mimeType;
    private final String extensionType;


    FileExtension(String mimeType, String extensionType) {
        this.mimeType = mimeType;
        this.extensionType = extensionType;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getExtensionType() {
        return this.extensionType;
    }

    public static FileExtension fromMimeType(String mimeType) {

        for (FileExtension fileExtension : values()) {

            if (fileExtension.getMimeType().equalsIgnoreCase(mimeType)) {

                return fileExtension;
            }
        }

        var errorMessage = String.format("File extension not found by mime type: %s", mimeType);
        throw new FileExtensionByMimeTypeNotFoundException(errorMessage);
    }
}
