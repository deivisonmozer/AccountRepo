package edu.miu.sa.reservation.repository;

import edu.miu.sa.reservation.entity.Account;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

class AccountRepositoryTest {
    @Mock
    private AccountRepository testRepo;
    private AutoCloseable autoCloseable;


    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testFindAll(){
        List<String> l = new ArrayList<>();
        l.add("visa");
        l.add("master");
        l.add("paypal");
        Account account = new Account("any street number","myemail@gmail.com", l);

        List<Account> list = testRepo.findAll();
        Assert.assertNotNull(list);
    }

    @Test
    void testSave(){
        List<String> paymentList = new ArrayList<>();
        paymentList.add("visa");
        paymentList.add("master");
        paymentList.add("paypal");

        Account account = new Account("any street number","myemail@gmail.com", paymentList);
        testRepo.save(account);

        ArgumentCaptor<Account> arg = ArgumentCaptor.forClass(Account.class);
        verify(testRepo).save(arg.capture());
    }
    @Test
    void testFindById(){
        List<String> l = new ArrayList<>();
        l.add("visa");
        l.add("master");
        l.add("paypal");

        Account account = new Account("any street number","myemail@gmail.com", l);
        testRepo.save(account);
        testRepo.findByEmail(account.getEmail());

        ArgumentCaptor<Account> arg = ArgumentCaptor.forClass(Account.class);
        verify(testRepo).findByEmail(account.getEmail());
    }

}