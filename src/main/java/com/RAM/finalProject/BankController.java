package com.RAM.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO) {
        try {
            Account createdAccount = bankService.createAccount(accountDTO);
            return ResponseEntity.status(201).body(createdAccount);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404)
                .body(new ErrorResponse("Customer not found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(new ErrorResponse("Error creating account", e.getMessage()));
        }
    }

    @GetMapping("/accounts/{accountNumber}")
    public ResponseEntity<?> getAccountByNumber(@PathVariable String accountNumber) {
        try {
            Account account = bankService.getAccountByNumber(accountNumber);
            return ResponseEntity.ok(account);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404)
                .body(new ErrorResponse("Account not found", e.getMessage()));
        }
    }

    @GetMapping("/accounts/customer/{customerId}")
    public ResponseEntity<?> getAccountsByCustomerId(@PathVariable Long customerId) {
        try {
            List<Account> accounts = bankService.getAccountsByCustomerId(customerId);
            return ResponseEntity.ok(accounts);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404)
                .body(new ErrorResponse("Customer not found", e.getMessage()));
        }
    }

    // New endpoint to get all accounts
    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts() {
        try {
            List<Account> accounts = bankService.getAllAccounts();
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(new ErrorResponse("Error retrieving accounts", e.getMessage()));
        }
    }
    
}
