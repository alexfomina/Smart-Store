package com.se300.ledger.repository;

import com.se300.ledger.SmartStoreApplication;
import com.se300.ledger.model.Account;
import com.se300.ledger.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {SmartStoreApplication.class})
public class AccountRepositoryMockTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testMockedTransactionRepository() {

        Account dummyPayer = new Account("payer", 0);
        Account dummyPayee = new Account("payee", 0);




        accountRepository.save(dummyPayer);
        assertEquals(dummyPayer, accountRepository.findById("payer").get());

    }
}
