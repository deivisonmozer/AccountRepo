package edu.miu.sa.reservation.service.configuration;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.repository.AccountRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.stream.Collectors;

@Configuration
public class KafkaConfiguration {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Bean
    public NewTopic newTopicGetAccountEvent() {
        return TopicBuilder.name("GET_ACCOUNT_EVENT")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic newTopicResponseGetAccountEvent() {
        return TopicBuilder.name("RESPONSE_GET_ACCOUNT_EVENT")
                .partitions(10)
                .replicas(1)
                .build();
    }

//    @KafkaListener(id = "myId", topics = "topic1")
//    public void listen(Account account) {
//        System.out.println("Received: " + account);
//    }

    @KafkaListener(id = "myId2", topics = "GET_ACCOUNT_EVENT")
    public void listenGetAccountEvent(Account account) {
        System.out.println("Received GET_ACCOUNT_EVENT: " + account);
        Account accountReturn = accountRepository.findAll().stream().filter(x->x.getEmail().equals(account.getEmail())).collect(Collectors.toList()).get(0);
        template.send("RESPONSE_GET_ACCOUNT_EVENT", accountReturn);
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, Account> template) {
        return args -> {
            template.send("topic1", new Account(123,"value@mail.com", "1000 n 50th street", new String[]{"visa", "master"}));
        };
    }
}
