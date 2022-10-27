package edu.miu.sa.reservation.controller;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.service.AccountService;
import edu.miu.sa.reservation.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/accounts"})
@Slf4j
@CrossOrigin
public class AccountController {
    @Value("${kafka.topic.created}")
    private String topicCreated;
    @Autowired
    private AccountService service;
    @Autowired
    private KafkaService kafkaService;

    @NewSpan("AccountService_addAccount")
    @PostMapping("/add")
    public String saveAccount(@RequestBody Account account) {
        service.save(account);
        kafkaService.send(topicCreated, account);
        return "Added account: " + account.toString();
    }
    @NewSpan("AccountService_findAll")
    @GetMapping({"/all"})
    public List<Account> getAccounts() {
        return service.findAll();
    }

    @NewSpan("AccountService_findByEmail")
    @GetMapping("/{email}")
    public List<String> getAccounts(@PathVariable("email") String email) {
        Optional<Account> account = service.findByEmail(email);
        if(account.isPresent())
            return account.get().getPayment();
        return Arrays.asList("No account found");
    }
}
