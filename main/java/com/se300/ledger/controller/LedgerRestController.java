package com.se300.ledger.controller;

import com.se300.ledger.model.Account;
import com.se300.ledger.model.Transaction;
import com.se300.ledger.service.LedgerAPI;
import com.se300.ledger.service.LedgerException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "basicAuth")
public class LedgerRestController {

    @Autowired
    private LedgerAPI ledger;

    @Operation(summary = "Create Account By Address", tags = {"accounts"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Account.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account) throws LedgerException {
        return ledger.createAccount(account.getAddress());
    }

    @Operation(summary = "Retrieve Account By Id", tags = {"accounts"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Account.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "There is no account with such address", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/accounts/{address}")
    public Account getAccount(@PathVariable String address) throws LedgerException {
        return ledger.getUncommittedBlock().getAccount(address);
    }

    @Operation(summary = "Retrieve Transaction By Id", tags = {"transactions"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Transaction.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/transactions/{transactionId}")
    public Transaction getTransaction(@PathVariable String transactionId){
        //TODO: Implement Transaction Retrieval REST Method
        return ledger.getTransaction(transactionId);
    }
    @Operation(summary = "Process Transaction", tags = {"transactions"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain")}),
            @ApiResponse(responseCode = "400", description = "Invalid transaction", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/transactions")
    public String processTransaction(@RequestBody Transaction transaction){

        //TODO: Implement Transaction Processing REST Method
        try{
            return ledger.processTransaction(transaction);}
        catch (LedgerException e)
        {
            return "Error processing transaction: " + e.getMessage();
        }
    }
}