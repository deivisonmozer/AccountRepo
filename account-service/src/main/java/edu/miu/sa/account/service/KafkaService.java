package edu.miu.sa.account.service;

import edu.miu.sa.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String, Object> template;
    public void send(String topicGet, Account account) {
        template.send(topicGet, account);
    }
}
