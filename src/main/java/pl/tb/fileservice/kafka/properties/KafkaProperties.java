package pl.tb.fileservice.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Getter
@Setter
public class KafkaProperties { //TODO: kafka producer/consumer configuration to be adjusted to this properties
    private String bootstrapServerURL;
    private KafkaTopicProperties fullReindexTopic;
}
