package edu.miu.sa.reservation.publisher;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Publisher {
    @Autowired
    private KafkaTemplate<String, Object> template;
    private String topic = "topic1";

    @GetMapping("/publishStudent")
    public String publishStudent() {
        Student student = new Student(2532, "User88");
        template.send(topic, student);
        return "published: " + student.toString();
    }
    @GetMapping("/publishAccountInKafka")
    public String publishAccount() {
        Account acc = new Account(123,"value@mail.com", "1000 n 50th street", new String[]{"visa", "master"});
        template.send(topic, acc);
        return "published: " + acc.toString();
    }

}
