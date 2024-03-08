package com.example.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking.entities.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

}
