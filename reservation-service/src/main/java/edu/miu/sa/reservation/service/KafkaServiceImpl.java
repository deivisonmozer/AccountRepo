package edu.miu.sa.reservation.service;

import edu.miu.sa.reservation.domain.KafkaMessage;
import edu.miu.sa.reservation.entity.Order;
import edu.miu.sa.reservation.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    private String topic;

    private final KafkaTemplate<String, Student> kafkaTemplate;

    @Override
    public void publish(String topic, Student message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    //@KafkaListener(id = "myId", topics = "topic1")
    public void listen(Student message) {
        System.out.println("Received: " + message);
    }
}

