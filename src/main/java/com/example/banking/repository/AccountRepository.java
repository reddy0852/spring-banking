package com.example.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.example.banking.entities.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer>{

	boolean findByCustomerIdAndPassword(int customerId, String password);

	List<Accounts> findByBalanceLessThan(double amount);

	List<Accounts> findByBalanceGreaterThan(double amount);

	Accounts findByAccountNo(int accountNo);

}
