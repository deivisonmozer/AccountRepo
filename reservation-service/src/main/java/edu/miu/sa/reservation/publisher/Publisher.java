package edu.miu.sa.reservation.publisher;

import edu.miu.sa.reservation.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Publisher {
    @Autowired
    private KafkaTemplate<String, Object> template;
    private String topic = "topic1";

    @GetMapping("/publishAccountInKafka")
    public String publishAccount() {
        Account acc = new Account(1,"value@mail.com", "", new String[]{""});
        template.send(topic, acc);
        return "published: " + acc.toString();
    }

}
