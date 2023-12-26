package com.se300.ledger.model.complete;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.Account;
import com.se300.ledger.model.Transaction;
import com.se300.ledger.model.Block;
import com.se300.ledger.service.LedgerAPI;
import com.se300.ledger.model.assertions.AssertionsTest;
import com.se300.ledger.model.stubs.StubTest;
import com.se300.ledger.service.Ledger;
import com.se300.ledger.service.LedgerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {TestSmartStoreApplication.class})
public class CompleteTest {

    @Autowired
    LedgerAPI ledgerAPI;

    /*
     * TODO: Must do the follows-ing
     * 1. Achieve 100% Test Coverage
     * 2. Produce/Print Identical Results to Command Line DriverTest
     * 3. Produce Quality Report
     */
    @Test
    void testAccountInequality() {
        Ledger ledger = new Ledger();
        Account mary = new Account("mary", 200);
        Account greg = new Account("greg", 100);
        assertFalse(mary.equals(greg));

    }

    @Test
    void testIsInstanceOfAccount() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("dummyAccount", 200);
        Account dummyAccount2 = new Account("dummyAccount2", 200);

        Transaction testTransaction2 = new Transaction("test2", 50, 10, "hello 2", dummyAccount2, dummyAccount);
        assertEquals(false, dummyAccount2.equals(testTransaction2));
    }

    @Test
    void testAccountHashCode() {
        Ledger ledger = new Ledger();
        Account s = new Account("s", 1000);
        Account f = new Account("f", 2000);
        assertEquals(false, s.hashCode() == f.hashCode());
    }

    @Test
    void testTransactionHashCode() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("sergey", 100);
        Account dummyAccount2 = new Account("test", 100);
        Transaction testTransaction = new Transaction("test", 50, 10, "hello", dummyAccount, dummyAccount2);
        Transaction testTransaction2 = new Transaction("test2", 50, 10, "hello 2", dummyAccount2, dummyAccount);
        assertEquals(false, testTransaction.hashCode() == testTransaction2.hashCode());
    }

    @Test
    void testSetTransactionId() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("sergey", 100);
        Account dummyAccount2 = new Account("test", 100);
        Transaction newTransaction = new Transaction("test", 50, 10, "hello", dummyAccount, dummyAccount2);
        newTransaction.setTransactionId("reset");
        assertEquals(true, newTransaction.getTransactionId().equals("reset"));

    }

    @Test
    void testSetTransactionAmount() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("sergey", 200);
        Account dummyAccount2 = new Account("test", 100);
        Transaction newTransaction = new Transaction("test", 50, 10, "hello", dummyAccount, dummyAccount2);
        newTransaction.setAmount(100);
        assertEquals(true, newTransaction.getAmount() == 100);
    }

    @Test
    void testSetFee() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("sergey", 100);
        Account dummyAccount2 = new Account("test", 100);
        Transaction newTransaction = new Transaction("test", 50, 10, "hello", dummyAccount, dummyAccount2);
        newTransaction.setFee(11);
        assertEquals(true, newTransaction.getFee() == 11);
    }

    @Test
    void testTransactionSetNote() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("sergey", 100);
        Account dummyAccount2 = new Account("test", 100);
        Transaction newTransaction = new Transaction("test", 50, 10, "hello", dummyAccount, dummyAccount2);
        newTransaction.setNote("newNote");
        assertEquals(true, newTransaction.getNote().equals("newNote"));
    }

    @Test
    void testTransactionSetPayer() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("sergey", 100);
        Account dummyAccount2 = new Account("test", 100);
        Account dummyAccount3 = new Account("max", 1000);
        Transaction newTransaction = new Transaction("test", 50, 10, "hello", dummyAccount, dummyAccount2);
        newTransaction.setPayer(dummyAccount3);
        assertEquals(true, newTransaction.getPayer().equals(dummyAccount3));
    }

    @Test
    void testTransactionSetPayee() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("sergey", 100);
        Account dummyAccount2 = new Account("test", 100);
        Account dummyAccount3 = new Account("max", 1000);
        Transaction newTransaction = new Transaction("test", 50, 10, "hello", dummyAccount, dummyAccount2);
        newTransaction.setReceiver(dummyAccount3);
        assertEquals(true, newTransaction.getReceiver().equals(dummyAccount3));
    }

    @Test
    void testTransactionInequalityEquality() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("sergey", 100);
        Account dummyAccount2 = new Account("test", 100);
        Transaction testTransaction = new Transaction("test", 50, 10, "hello", dummyAccount, dummyAccount2);
        Transaction testTransaction2 = new Transaction("test2", 50, 10, "hello 2", dummyAccount2, dummyAccount);
        assertFalse(testTransaction2.equals(testTransaction));
    }

    @Test
    void testTransactionEquality() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("sergey", 1000);
        Account dummyAccount2 = new Account("test", 1000);
        Transaction testTransaction2 = new Transaction("test2", 50, 10, "hello", dummyAccount, dummyAccount2);
        assertEquals(true, testTransaction2.equals(testTransaction2));
    }

    @Test
    void testIsInstanceOfTransaction() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("dummyAccount", 200);
        Account dummyAccount2 = new Account("dummyAccount2", 200);

        Transaction testTransaction2 = new Transaction("test2", 50, 10, "hello 2", dummyAccount2, dummyAccount);
        assertEquals(false, testTransaction2.equals(dummyAccount2));
    }

    @Test
    void testSetBlockNumber() {
        Ledger ledger = new Ledger();
        Block myB = new Block(100, "hash");
        myB.setBlockNumber(200);
        assertEquals(true, myB.getBlockNumber() == 200);
    }

    @Test
    void testSetPreviousHash() {
        Ledger ledger = new Ledger();
        Block myB = new Block(100, "hash");
        myB.setPreviousHash("newHash");
        assertEquals(true, myB.getPreviousHash().equals("newHash"));
    }

    @Test
    void testGetPreviousHash() {
        Ledger ledger = new Ledger();
        Block myB = new Block(100, "hash");
        assertEquals(true, myB.getPreviousHash().equals("hash"));
    }

    @Test
    void testPreviousBlock() {
        Ledger ledger = new Ledger();
        Block myB = new Block(100, "hash");
        assertEquals(true, myB.getPreviousBlock() == null);
    }

    @Test
    void testSetLedgerName() {
        Ledger ledger = new Ledger();
        ledger.setName("steve");
        assertEquals(true, ledger.getName().equals("steve"));
    }

    @Test
    void testSetLedgerDescription() {
        Ledger ledger = new Ledger();
        ledger.setDescription("new description");
        assertEquals(true, ledger.getDescription().equals("new description"));
    }

    @Test
    void setLedgerSeed() {
        Ledger ledger = new Ledger();
        ledger.setSeed("new seed");
        assertEquals(true, ledger.getSeed().equals("new seed"));
    }

    @Test
    void testNumberOfBlocks() {
        Ledger ledger = new Ledger();
        assertEquals(true, ledger.getNumberOfBlocks() == 0);
    }

    @Test
    void testLedgerGetAccountBalancesNull() {
        Ledger ledger = new Ledger();
        assertEquals(true, ledger.getAccountBalances() == null);
    }

    @Test
    void testLedgerRestController() {
        Ledger ledger = new Ledger();
        assertEquals(true, ledgerAPI.getTransaction("100") == null);
    }

    @Test
    public void testTransactionAmountOutOfRange() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("dummyAccount", 200);
        Account dummyAccount2 = new Account("dummyAccount2", 200);

        Transaction transaction = new Transaction("test2", -100, 10, "hello 2", dummyAccount, dummyAccount2);
        //Transaction transaction = new Transaction(-100, 15, "Valid note");

        LedgerException exception = Assertions.assertThrows(LedgerException.class, () -> {
            ledger.processTransaction(transaction);
        });

        //Assertions.assertEquals("Process Transaction", exception.getReason());
        Assertions.assertEquals("Transaction Amount Is Out of Range", exception.getReason());
    }

    @Test
    public void testTransactionFeeLessThanTen() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("dummyAccount", 200);
        Account dummyAccount2 = new Account("dummyAccount2", 200);

        Transaction transaction = new Transaction("test2", 50, 5, "hello 2", dummyAccount, dummyAccount2);

        LedgerException exception = Assertions.assertThrows(LedgerException.class, () -> {
            ledger.processTransaction(transaction);
        });

        //assertEquals("Process Transaction", exception.getReason());
        Assertions.assertEquals("Transaction Fee Must Be Greater Than 10", exception.getReason());
    }

    @Test
    public void testNoteLengthExceedsLimit() {
        Ledger ledger = new Ledger();
        //Ledger ledger = new Ledger();
        Account dummyAccount = new Account("dummyAccount", 200);
        Account dummyAccount2 = new Account("dummyAccount2", 200);
        String noteWithExceedingLength = "A".repeat(1025); // Create a note with length > 1024 characters

        Transaction transaction = new Transaction("test 2", 100, 20, noteWithExceedingLength, dummyAccount, dummyAccount2);

        LedgerException exception = Assertions.assertThrows(LedgerException.class, () -> {
            ledger.processTransaction(transaction);
        });

        Assertions.assertEquals("Note Length Must Be Less Than 1024 Chars", exception.getReason());
    }

    @Test
    public void testGetAccountBalanceEmptyBlockMap() {
        Ledger ledger = new Ledger();
        String address = "dummyAddress";
        Account myAcount = null;

        LedgerException exception = Assertions.assertThrows(LedgerException.class, () -> {
            ledger.getAccountBalance(address);
        });
        Assertions.assertEquals("Get Account Balance", exception.getAction());

        Assertions.assertEquals("Account Is Not Committed to a Block", exception.getReason());
    }

//    @Test
//    public void testHashConsistency() {
//        Ledger ledger = new Ledger();
//        try {
//            // Create blocks with inconsistent hashes for testing
//            Block block1 = new Block(1, "Hash1");
//            Block block2 = new Block(2, "InvalidHash2");
//
//            // Set block2's previous block to block1
//            block2.setPreviousBlock(block1);
//
//            // Add blocks to the ledger
//            Ledger.getBlockMap().put(1, block1);
//            Ledger.getBlockMap().put(2, block2);
//
//            // Attempt to validate
//            ledger.validate();
//
//            // If validation passes, the test should fail
//            fail("Expected LedgerException for inconsistent hashes.");
//        } catch (LedgerException e) {
//            // Successful test if LedgerException is caught
//            assertEquals("Hash Is Inconsistent: 2", e.getMessage());
//        }
//    }


//    @Test
//    public void testTransactionCount() {
//        try {
//            // Create a block with an invalid number of transactions
//            Block invalidBlock = new Block(1, "Hash");
//            for (int i = 0; i < 5; i++) {
//                invalidBlock.getTransactionList().add(new Transaction());
//            }
//
//            // Add the invalid block to the ledger
//            Ledger.getBlockMap().put(1, invalidBlock);
//
//            // Attempt to validate
//            ledger.validate();
//
//            // If validation passes, the test should fail
//            fail("Expected LedgerException for invalid transaction count.");
//        } catch (LedgerException e) {
//            // Successful test if LedgerException is caught
//            assertEquals("Transaction Count Is Not 10 In Block: 1", e.getMessage());
//        }
//    }

    @Test
    void testGetBlockNonexistentBlock() {
        Ledger ledger = new Ledger();
        // Arrange
        int nonExistentBlockNumber = 999;

        // Act and Assert
        LedgerException ledgerException = assertThrows(LedgerException.class, () -> {
            ledger.getBlock(nonExistentBlockNumber);
        });

        assertEquals("Get Block", ledgerException.getAction());
        assertEquals("Block Does Not Exist", ledgerException.getReason());
    }



    @Test
    public void testTransactionAmountOutOfRange2() {
        Ledger ledger = new Ledger();
        Account dummyAccount = new Account("dummyAccount", Integer.MAX_VALUE + 1);
        Account dummyAccount2 = new Account("dummyAccount2", 200);

        Transaction transaction = new Transaction("test2", Integer.MAX_VALUE + 1, 10, "hello 2", dummyAccount, dummyAccount2);

        LedgerException exception = Assertions.assertThrows(LedgerException.class, () -> {
            ledger.processTransaction(transaction);
        });
        //Assertions.assertEquals("Process Transaction", exception.getReason());
        Assertions.assertEquals("Transaction Amount Is Out of Range", exception.getReason());
    }




}