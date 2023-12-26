package com.se300.ledger.repository;

import com.se300.ledger.SmartStoreApplication;
import com.se300.ledger.model.Account;
import com.se300.ledger.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {SmartStoreApplication.class})
public class AccountRepositoryIntegrationTest {


    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testAccountRepository(){

        Account payer = new Account("payer", 60);
        Account payee = new Account("payee", 0);

        accountRepository.save(payer);
        accountRepository.save(payee);


        assertEquals (payer, accountRepository.findById("payer").get());
        assertEquals (payee, accountRepository.findById("payee").get());
    }
}
