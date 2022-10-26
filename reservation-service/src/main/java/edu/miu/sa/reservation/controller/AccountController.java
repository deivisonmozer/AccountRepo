package edu.miu.sa.reservation.controller;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.service.AccountService;
import edu.miu.sa.reservation.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class AccountController {
    @Value("${kafka.topic.created}")
    private String topicCreated;
    @Autowired
    private AccountService service;
    @Autowired
    private KafkaService kafkaService;

    @NewSpan("AccountService_addAccount")
    @PostMapping("/addAccount")
    public String saveAccount(@RequestBody Account account) {
        service.save(account);
        kafkaService.send(topicCreated, account);
        return "Added account with id : " + account.getId();
    }
    @NewSpan("AccountService_findAllAccounts")
    @GetMapping("/findAllAccounts")
    public List<Account> getAccounts() {
        return service.findAll();
    }

    @NewSpan("AccountService_findById")
    @GetMapping("/findAllAccounts/{id}")
    public Optional<Account> getAccounts(@PathVariable int id) {
        return service.findById(id);
    }

}
