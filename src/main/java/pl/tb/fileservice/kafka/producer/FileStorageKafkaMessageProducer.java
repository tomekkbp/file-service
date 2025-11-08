package pl.tb.fileservice.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.tb.fileservice.kafka.message.FileStorageKafkaMessage;
import pl.tb.fileservice.storage.config.enumerates.FileGroup;

@Component
@RequiredArgsConstructor
public class FileStorageKafkaMessageProducer {

    private final KafkaTemplate<String, String> template;
    private final ObjectMapper objectMapper;

    public void send(FileGroup fileGroup, FileStorageKafkaMessage message) {

        try {

            var topic = switch (fileGroup) {
                case RECEIPT_EXCEL -> "file-storage-service.receipt-excel.v1";
                case RECEIPT_IMAGE -> "file-storage-service.receipt-image.v1";
            };

            var stringMessage = objectMapper.writeValueAsString(message);
            template.send(topic, stringMessage);

        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }

    }

}
