package edu.miu.sa.reservation.integration;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.service.KafkaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;

@Testcontainers
@SpringBootTest
@ActiveProfiles("dev")
public class KafkaIntegrationTest {
    @Value("${kafka.topic.get}")
    private String topicGet;

    @Autowired
    private KafkaService kafkaService;

    @Container
    static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.0.1"));

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Test
    public void should_publish() throws InterruptedException {
        List<String> l = new ArrayList<>();
        l.add("visa");
        Account account = new Account("any street number","myemail@gmail.com", l);
        kafkaService.send(topicGet, account);
    }
}
