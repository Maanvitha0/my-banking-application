DROP DATABASE IF EXISTS bankdb3;  -- Drop existing database if it exists
CREATE DATABASE bankdb3;          -- Create new database
USE bankdb3;                      -- Use the new database

CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    address VARCHAR(255),
    phone VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE accounts (
    account_number VARCHAR(20) PRIMARY KEY,
    customer_id INT,
    balance DECIMAL(10, 2),
    account_type VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    account_number VARCHAR(20),
    transaction_type VARCHAR(50),
    amount DECIMAL(10, 2),
    transaction_date DATE,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)  -- Corrected
);

CREATE TABLE loans (
    loan_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    loan_amount DECIMAL(12, 2),
    interest_rate DECIMAL(4, 2),
    loan_term_years INT,
    loan_status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE deposits (
    deposit_id INT PRIMARY KEY AUTO_INCREMENT,
    account_number VARCHAR(20),
    amount DECIMAL(10, 2),
    deposit_date DATE,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)  -- Corrected
);

CREATE TABLE withdrawals (
    withdrawal_id INT PRIMARY KEY AUTO_INCREMENT,
    account_number VARCHAR(20),
    amount DECIMAL(10, 2),
    withdrawal_date DATE,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)  -- Corrected
);

-- Sample Data
INSERT INTO customers (id, name, address, phone, email) VALUES
(1, 'John Doe', '123 Elm Street', '123-456-7890', 'john.doe@example.com'),
(2, 'Jane Smith', '456 Oak Avenue', '987-654-3210', 'jane.smith@example.com'),
(3, 'Michael Johnson', '789 Pine Road', '555-123-4567', 'michael.johnson@example.com');

INSERT INTO accounts (account_number, customer_id, balance, account_type) VALUES
('ACC1001', 1, 5000.00, 'Savings'),
('ACC1002', 2, 10000.00, 'Checking'),
('ACC1003', 3, 1500.00, 'Savings');

INSERT INTO transactions (transaction_id, account_number, transaction_type, amount, transaction_date) VALUES
(1, 'ACC1001', 'Deposit', 1000.00, '2024-10-20'),
(2, 'ACC1002', 'Withdrawal', 500.00, '2024-10-21'),
(3, 'ACC1003', 'Transfer', 200.00, '2024-10-22');

INSERT INTO loans (loan_id, customer_id, loan_amount, interest_rate, loan_term_years, loan_status) VALUES
(1, 1, 25000.00, 5.5, 5, 'Approved'),
(2, 2, 15000.00, 4.0, 3, 'Pending'),
(3, 3, 50000.00, 6.0, 7, 'Approved');

INSERT INTO deposits (deposit_id, account_number, amount, deposit_date) VALUES
(1, 'ACC1001', 2000.00, '2024-10-21'),
(2, 'ACC1002', 3000.00, '2024-10-22'),
(3, 'ACC1003', 500.00, '2024-10-23');

INSERT INTO withdrawals (withdrawal_id, account_number, amount, withdrawal_date) VALUES
(1, 'ACC1001', 1000.00, '2024-10-24'),
(2, 'ACC1002', 500.00, '2024-10-25'),
(3, 'ACC1003', 100.00, '2024-10-26');

-- List all tables in the current database
SHOW TABLES;
