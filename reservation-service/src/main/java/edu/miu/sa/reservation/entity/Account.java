package edu.miu.sa.reservation.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "account")
public class Account {
    @Id
    private int id;
    private String email;
    private String address;
    private String[] payment;
}
