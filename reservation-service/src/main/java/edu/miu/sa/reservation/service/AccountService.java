package edu.miu.sa.reservation.service;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public Account findById(int id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find account: " + id));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(int id) {
        accountRepository.deleteById(id);
    }
}
