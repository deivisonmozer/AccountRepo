package edu.miu.sa.reservation.configuration;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.repository.AccountRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${kafka.topic.response}")
    private String topicResponse;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KafkaTemplate<String, Object> template;

    @KafkaListener(id = "myId2", topics = "GetAccountEvent")
    public void listenGetAccountEvent(Account account) {
        System.out.println("Received GET_ACCOUNT_EVENT: " + account);
        List<Account> accountReturn = accountRepository.findAll().stream().filter(x->x.getEmail().equals(account.getEmail())).collect(Collectors.toList());
        if(accountReturn != null && accountReturn.size() > 0)
            template.send(topicResponse, accountReturn.get(0));
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, Account> template) {
        return args -> {
            //template.send("topic1", new Account("value@mail.com", "1000 n 50th street", new String[]{"visa", "master"}));
        };
    }
}
