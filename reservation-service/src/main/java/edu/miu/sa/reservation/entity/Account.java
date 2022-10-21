package edu.miu.sa.reservation.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    private String email;
    private String address;
    private String[] payment;
}
