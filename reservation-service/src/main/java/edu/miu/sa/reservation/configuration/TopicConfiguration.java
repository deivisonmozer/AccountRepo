package edu.miu.sa.reservation.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("dev")
public class TopicConfiguration {
    @Value("${kafka.topic.response}")
    private String topicResponse;
    @Value("${kafka.topic.get}")
    private String topicget;
    @Value("${kafka.topic.created}")
    private String topiccreated;

    @Bean
    public NewTopic newTopicGetAccountEvent() {
        return TopicBuilder.name(topicget)
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic newTopicResponseGetAccountEvent() {
        return TopicBuilder.name(topicResponse)
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic newTopicCreatedAccountEvent() {
        return TopicBuilder.name(topiccreated)
                .partitions(10)
                .replicas(1)
                .build();
    }

}
