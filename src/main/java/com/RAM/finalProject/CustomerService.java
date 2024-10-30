package com.RAM.finalProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(CustomerDTO customerDTO) {
        // Check if the customer already exists
        if (customerRepository.findByEmail(customerDTO.getEmail()) != null) {
            throw new CustomerAlreadyExistsException("Customer with email " + customerDTO.getEmail() + " already exists.");
        }

        try {
            // Create a new customer instance
            Customer customer = new Customer();
            customer.setName(customerDTO.getName());
            customer.setAddress(customerDTO.getAddress());
            customer.setPhone(customerDTO.getPhone());
            customer.setEmail(customerDTO.getEmail());

            // Save the customer to the repository
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("Error creating customer: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {
        try {
            List<Customer> customers = customerRepository.findAll();
            if (customers.isEmpty()) {
                throw new EntityNotFoundException("No customers found");
            }
            return customers;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving customers: " + e.getMessage());
        }
    }



}
