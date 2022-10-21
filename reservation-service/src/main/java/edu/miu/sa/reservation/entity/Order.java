package edu.miu.sa.reservation.entity;


import edu.miu.sa.reservation.domain.KafkaMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements KafkaMessage {

    private String paymentType;

    private int propertyId;

    private double price;

    private int night;

}
