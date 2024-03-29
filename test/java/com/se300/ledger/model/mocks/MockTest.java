package com.se300.ledger.model.mocks;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.*;
import com.se300.ledger.service.Ledger;
import com.se300.ledger.service.LedgerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {TestSmartStoreApplication.class})
public class MockTest {

    @Autowired
    Ledger ledger;

    @Test
    void testPayerBalanceCheck() throws LedgerException {

        Account mary = mock(Account.class);
        Account sergey = mock(Account.class);

        Transaction sampleTransaction =
                new Transaction("1",1000,10,"simple test", mary, sergey);

        assertThrows(LedgerException.class, () -> ledger.processTransaction(sampleTransaction));

        verify(mary, times(1)).getBalance();

    }


//ADDED BELOW
    @Test
    void testTransactionOutOfRangeCheck() throws LedgerException{
        Ledger ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");

        Account steve = mock(Account.class);
        Account greg = mock(Account.class);

        Transaction sampleTransaction =
                mock(Transaction.class); //Transaction("2", 1000, 10, "simple test 2", steve, greg);

        assertThrows(LedgerException.class, () -> ledger.processTransaction(sampleTransaction));

        verify(sampleTransaction, times(2)).getAmount();
    }




}
