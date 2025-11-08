package pl.tb.fileservice.kafka.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaTopicProperties {
    private String topicName;
    private String groupName;
}
