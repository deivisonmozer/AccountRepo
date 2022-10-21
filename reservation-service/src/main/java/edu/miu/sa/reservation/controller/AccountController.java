package edu.miu.sa.reservation.controller;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository repository;

    @PostMapping("/addAccount")
    public String saveAccount(@RequestBody Account account) {
        repository.save(account);
        return "Added account with id : " + account.getId();
    }

    @GetMapping("/findAllAccounts")
    public List<Account> getAccounts() {
        return repository.findAll();
    }

    @GetMapping("/findAllAccounts/{id}")
    public Optional<Account> getAccounts(@PathVariable int id) {
        return repository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id) {
        repository.deleteById(id);
        return "Account deleted with id : " + id;
    }
}
