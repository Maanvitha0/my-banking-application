package com.RAM.finalProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Customer createdCustomer = customerService.createCustomer(customerDTO);
            return ResponseEntity.status(201).body(createdCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(new ErrorResponse("Error creating customer", e.getMessage()));
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            if (customers.isEmpty()) {
                return ResponseEntity.status(404)
                    .body(new ErrorResponse("No customers found", "The customer list is empty"));
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                .body(new ErrorResponse("Error retrieving customers", e.getMessage()));
        }
    }



}
