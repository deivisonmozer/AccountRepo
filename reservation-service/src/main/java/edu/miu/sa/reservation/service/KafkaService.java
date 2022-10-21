package edu.miu.sa.reservation.service;

import edu.miu.sa.reservation.domain.KafkaMessage;
import edu.miu.sa.reservation.entity.Order;
import edu.miu.sa.reservation.entity.Student;

public interface KafkaService {

    void publish(String topic, Student message);

    void listen(Student message);
}
