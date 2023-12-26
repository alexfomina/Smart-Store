package com.se300.ledger.model;

import com.se300.ledger.SmartStoreApplication;
import com.se300.ledger.service.LedgerException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {SmartStoreApplication.class})
public class TransactionTest {

    @Test
    void testTransactionInstantiation() throws LedgerException {

        Account dummyAccount = new Account("sergey", 100);
        Account dummyAccount2 = new Account("test", 100);
        Transaction testTransaction = new Transaction("test", 50, 10, "hello", dummyAccount, dummyAccount2);
        assertAll("Verify Account properties",
                () -> assertEquals("test", testTransaction.getTransactionId()),
                () -> assertEquals(50, testTransaction.getAmount()),
                () -> assertEquals(10, testTransaction.getFee()),
                () -> assertEquals("hello", testTransaction.getNote()),
                () -> assertEquals(dummyAccount, testTransaction.getPayer()),
                () -> assertEquals(dummyAccount2, testTransaction.getReceiver()));



    }
}
