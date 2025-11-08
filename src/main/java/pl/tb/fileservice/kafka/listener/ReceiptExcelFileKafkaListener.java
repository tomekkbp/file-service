package pl.tb.fileservice.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.tb.fileservice.kafka.message.FileStorageKafkaMessage;

@Component
@RequiredArgsConstructor
public class ReceiptExcelFileKafkaListener {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topics.receiptExcelFileStorageTopicName}", groupId = "file-service")
    public void aaa(ConsumerRecord<String, String> message) {

        try {
            FileStorageKafkaMessage fullReindexKafkaMessage = objectMapper.readValue(message.value(), FileStorageKafkaMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
