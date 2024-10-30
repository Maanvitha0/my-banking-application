package com.RAM.finalProject;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;

@Service
public class BankService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Account createAccount(AccountDTO accountDTO) {
        // First validate if customer exists
        Customer customer = customerRepository.findById(accountDTO.getCustomerId())
            .orElseThrow(() -> new EntityNotFoundException(
                "Customer not found with ID: " + accountDTO.getCustomerId()));

        // Create new account
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance());
        account.setAccountType(accountDTO.getAccountType());
        account.setCustomer(customer);

        // Save and return the account
        return accountRepository.save(account);
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findById(accountNumber)
            .orElseThrow(() -> new EntityNotFoundException(
                "Account not found with number: " + accountNumber));
    }

    public List<Account> getAccountsByCustomerId(Long customerId) {
        // Verify customer exists first
        customerRepository.findById(customerId)
            .orElseThrow(() -> new EntityNotFoundException(
                "Customer not found with ID: " + customerId));
        return accountRepository.findByCustomer_Id(customerId);
    }

    // New method to get all accounts with exception handling
    public List<Account> getAllAccounts() {
        try {
            return accountRepository.findAll(); // Fetch all accounts from the database
        } catch (PersistenceException e) {
            // Log the error and rethrow as a custom exception if needed
            throw new RuntimeException("Error retrieving accounts from the database", e);
        }
    }
    
}
