package edu.miu.sa.account.configuration;

import edu.miu.sa.account.entity.Account;
import edu.miu.sa.account.repository.AccountRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
//@EnableKafka
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

    @Bean
    public NewTopic newTopicAccountCreatedEvent() {
        return TopicBuilder.name("ACCOUNT_CREATED_EVENT")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(id = "myId2", topics = "GET_ACCOUNT_EVENT")
    public void listenGetAccountEvent(Account account) {
        System.out.println("Received GET_ACCOUNT_EVENT: " + account);
        List<Account> accountReturn = accountRepository.findAll().stream().filter(x->x.getEmail().equals(account.getEmail())).collect(Collectors.toList());
        if(accountReturn != null && accountReturn.size() > 0)
            template.send("RESPONSE_GET_ACCOUNT_EVENT", accountReturn.get(0));
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, Account> template) {
        return args -> {
        };
    }
}
