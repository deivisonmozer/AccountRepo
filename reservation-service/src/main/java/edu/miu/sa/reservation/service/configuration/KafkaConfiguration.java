package edu.miu.sa.reservation.service.configuration;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.entity.Student;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(Account account) {
        System.out.println("Received: " + account);
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, Account> template) {
        return args -> {
//            template.send("topic1", new Student(1, "Phat"));
            template.send("topic1", new Account("value@mail.com", "1000 n 50th street", new String[]{"visa", "master"}));
        };
    }
}
