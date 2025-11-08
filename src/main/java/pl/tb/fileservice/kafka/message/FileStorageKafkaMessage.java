package pl.tb.fileservice.kafka.message;

import pl.tb.fileservice.storage.config.enumerates.FileGroup;

public record FileStorageKafkaMessage(
        FileGroup fileGroup,
        String path,
        Boolean isDeleted) {
}
