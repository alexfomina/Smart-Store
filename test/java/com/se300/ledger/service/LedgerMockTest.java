package com.se300.ledger.service;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.Account;
import com.se300.ledger.model.Transaction;
import com.se300.ledger.repository.AccountRepository;
import com.se300.ledger.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {TestSmartStoreApplication.class})
public class LedgerMockTest {

    @Autowired
    private Ledger ledger;
    @Autowired
    private Ledger ledger1;
//    @Autowired
//    private Transaction transaction;

    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private TransactionRepository transactionRepository;

//    @Test //issue when running testGetAllAccount and getAllTransaction, but each run individually
//    public void testGetAllAccount() throws LedgerException {
//        List<Account> list = new ArrayList<Account>();
//        Account dummyPayer = new Account("payer", 0);
//        Account dummyPayee = new Account("payee", 0);
//        list.add(dummyPayer);
//        list.add(dummyPayee);
//
//        given(accountRepository.save(any(Account.class))).willReturn(dummyPayee);
//        ledger.createAccount(dummyPayee.getAddress());
//        ledger.createAccount(dummyPayer.getAddress());
//        verify(accountRepository, times(1)).save(dummyPayee);
//
//        Collection<Account> accountList = ledger.getUncommittedBlock().getAccountBalanceMap().values();
//
//        List<Account> differences = list.stream()
//                .filter(element -> !accountList.contains(element))
//                .collect(Collectors.toList());
//        assertEquals(0, differences.size());
//    }



    @Test
    public void testGetAllTransaction() throws LedgerException {
        Account dummyPayer = new Account("payer", 200);
        Account dummyPayee = new Account("payee", 200);

        List<Transaction> list = new ArrayList<Transaction>();
        Transaction dummyTransaction = new Transaction("id", 100, 15, "test", dummyPayer, dummyPayee);
        list.add(dummyTransaction);

        given(transactionRepository.save(any(Transaction.class))).willReturn(dummyTransaction);
        ledger1.createAccount(dummyPayee.getAddress());
        ledger1.createAccount(dummyPayer.getAddress());
        ledger1.processTransaction(dummyTransaction);
        verify(transactionRepository, times(1)).save(dummyTransaction);

        System.out.println(ledger1.getUncommittedBlock().getTransactionList());
        List<Transaction> transactionList = ledger1.getUncommittedBlock().getTransactionList();


        List<Transaction> differences = list.stream().filter(element -> !transactionList.contains(element)).toList();
        assertEquals(0, differences.size());

    }
}