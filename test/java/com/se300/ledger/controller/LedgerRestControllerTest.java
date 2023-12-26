package com.se300.ledger.controller;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.Account;
import com.se300.ledger.model.Transaction;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import javax.sound.midi.Receiver;

@SpringBootTest(classes = TestSmartStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LedgerRestControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static HttpHeaders headers;

    @BeforeAll
    static void init(){

        headers = new HttpHeaders();
        headers.setBasicAuth("sergey", "chapman");
    }

    @Test
    public void testGetAccountById() throws IllegalStateException, JSONException {

        String expectedJson = "{\"address\" : \"master\", \"balance\" : 2147483647}";

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/accounts/master", HttpMethod.GET, new HttpEntity<String>(headers),
                String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }

    @Test
    public void testPostAccount() throws IllegalStateException, JSONException {

        String expectedJson = "{\"address\" : \"sergey\", \"balance\" : 0}";
        Account account = new Account("sergey", 0);

        HttpEntity<Account> request = new HttpEntity<>(account, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/accounts", request, String.class);
        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }

    @Test
    public void testGetTransactionById() throws IllegalStateException, JSONException {

        Transaction transaction = new Transaction("11", 40, 10, "This is a test",
                new Account("master", 60), new Account("sergey", 10));

        HttpEntity<Transaction> getRequest = new HttpEntity<>(transaction, headers);

        restTemplate.postForEntity("http://localhost:" + port + "/transactions", getRequest, String.class);

        String expectedJson = "{\"transactionId\":\"11\",\"amount\":40,\"fee\":10,\"note\":\"This is a test\",\"payer\"" +
                ":{\"address\":\"master\",\"balance\":2147483647},\"receiver\":{\"address\":\"sergey\",\"balance\":0}}";

        ResponseEntity<String> postResponse = restTemplate.exchange(
                "http://localhost:" + port + "/transactions/11", HttpMethod.GET, new HttpEntity<String>(headers),
                String.class);

        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        JSONAssert.assertEquals(expectedJson, postResponse.getBody(),true);
    }

    @Test
    public void testPostTransaction() throws IllegalStateException, JSONException{

        String expectedJson = "10";
        Transaction transaction = new Transaction("10", 40, 10, "This is a test",
                new Account("master", 60), new Account("sergey", 10));

        HttpEntity<Transaction> request = new HttpEntity<>(transaction, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/transactions", request, String.class);

        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }
}





















