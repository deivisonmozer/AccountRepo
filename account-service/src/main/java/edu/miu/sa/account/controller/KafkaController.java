package edu.miu.sa.account.controller;

import edu.miu.sa.account.entity.Account;
import edu.miu.sa.account.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    private KafkaService kafkaService;
//    @Value("${kafka.topic.get}")
    private String topicGet = "GET_ACCOUNT_EVENT";
//    @Value("${kafka.topic.response}")
    private String topicResponse = "RESPONSE_GET_ACCOUNT_EVENT";

    //test publish Account in kafka topic
    @PostMapping("/publishAccountInKafkaGet")
    public String putInTopicGet(@RequestBody Account account) {
        kafkaService.send(topicGet, account);
        return "Added to kafka topic " + topicGet + ": " + account;
    }
    //test publish Account in kafka topic
    @PostMapping("/publishAccountInKafkaResponse")
    public String putInTopicResponse(@RequestBody Account account) {
        kafkaService.send(topicResponse, account);
        return "Added to kafka topic " + topicResponse + ": " + account;
    }
}
