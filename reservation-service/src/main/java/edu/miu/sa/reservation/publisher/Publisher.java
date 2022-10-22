package edu.miu.sa.reservation.publisher;

import edu.miu.sa.reservation.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Publisher {
    @Autowired
    private KafkaTemplate<String, Object> template;

    @Value("${kafka.topic.get}")
    private String topicGet;

    @Value("${kafka.topic.response}")
    private String topicResponse;

    //test publish default account in kafka
//    @GetMapping("/publishAccountInKafka")
//    public String publishAccount() {
//        List<String> list = new ArrayList<String>();
//        list.add("visa");
//        Account acc = new Account(123,"value@mail.com", "", list); //
//        template.send(topicGet, acc);
//        return "published: " + acc.toString();
//    }
    //test publish Account in kafka topic
    @PostMapping("/publishAccountInKafkaGet")
    public String putInTopicGet(@RequestBody Account account) {
        template.send(topicGet, account);
        return "Added to kafka topic " + topicGet + ": " + account;
    }
    //test publish Account in kafka topic
    @PostMapping("/publishAccountInKafkaResponse")
    public String putInTopicResponse(@RequestBody Account account) {
        template.send(topicResponse, account);
        return "Added to kafka topic " + topicResponse + ": " + account;
    }
}
