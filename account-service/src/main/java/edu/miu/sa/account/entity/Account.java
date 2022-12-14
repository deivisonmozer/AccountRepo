package edu.miu.sa.account.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Account {
    @PrimaryKey
    private int id;
    private String email;
    private String address;
    @Column
    private List<String> payment;
}
