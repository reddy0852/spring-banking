package com.example.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.example.banking.entities.Accounts;
import com.example.banking.entities.Transactions;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;

@RestController
public class AccountController {

	private final AccountRepository accountRepo;
	private final TransactionRepository transactionRepo;

	@Autowired
	public AccountController(AccountRepository accountRepository, TransactionRepository transactionRepo) {
		this.accountRepo = accountRepository;
		this.transactionRepo = transactionRepo;
	}

	@PostMapping("/login")
	public boolean postLoginDetails(@RequestBody LoginRequest loginRequest) {
		boolean accountExists = accountRepo.findByCustomerIdAndPassword(loginRequest.getCustomerId(),
				loginRequest.getPassword());
		return accountExists;
	}

	@PostMapping("/register")
	public String registerAccount(@RequestBody Accounts account) {
		if (accountRepo.existsById(account.getCustomerId())) {
			return "Account with Customer ID " + account.getCustomerId() + " already exists";
		}
		accountRepo.save(account);
		return "Registration successfully completed with provided details";
	}

	@GetMapping("/changepwd/{cid}/{abc}/{xyz}")
	public String getChangePassword(@PathVariable("cid") int customerId, @PathVariable("abc") String oldPassword,
			@PathVariable("xyz") String newPassword) {
		Optional<Accounts> optionalAccount = accountRepo.findById(customerId);

		if (optionalAccount.isPresent()) {
			Accounts account = optionalAccount.get();
			String lastPassword = account.getLastPassword();

			if (!oldPassword.equals(account.getPassword())) {
				return "Old password is incorrect !!!!";
			}
			if (!newPassword.equals(lastPassword)) {
				return "The new password is matching with last used in the account...!!";
			}
			account.setPassword(newPassword);
			account.setLastPassword(oldPassword);

			accountRepo.save(account);

			return "Password changed successfully...";
		} else {
			return "Customer ID not found";
		}
	}

	@GetMapping("/accounts/below")
	public List<Accounts> getAccountsBelowBalance(@RequestParam("amount") double amount) {
		List<Accounts> accounts = accountRepo.findByBalanceLessThan(amount);
		return accounts;
	}

	@GetMapping("/accounts/above")
	public List<Accounts> getAccountsAboveBalance(@RequestParam("amount") double amount) {
		List<Accounts> accounts = accountRepo.findByBalanceGreaterThan(amount);
		return accounts;
	}

	@GetMapping("/balance/accountNo")
	public String getBalance(@RequestParam("accountNo") int accountNo) {
		Accounts account = accountRepo.findByAccountNo(accountNo);

		if (account == null) {
			return "The account with the requested accountNo doesn't exists: " + accountNo;
		}

		return "The balance in the account with account number :" + accountNo + " is -" + account.getBalance();
	}

	@PostMapping("/transfer")
	public String transferFunds(@RequestParam("fromAcctNo") int fromAcctno, @RequestParam("toAcctNo") int toAcctno,
			@RequestParam("ifsc") int ifsc, @RequestParam("amount") double amount) {

		Accounts fromAccount = accountRepo.findByAccountNo(fromAcctno);
		Accounts toAccount = accountRepo.findByAccountNo(toAcctno);

		if (fromAccount == null || toAccount == null) {
			return "One or both accounts do not exist";
		}

		if (fromAccount.getBalance() < amount) {
			return "Insufficient balance in the sender's account";
		}

		double updatedFromBalance = fromAccount.getBalance() - amount;
		double updatedToBalance = toAccount.getBalance() + amount;

		fromAccount.setBalance(updatedFromBalance);
		toAccount.setBalance(updatedToBalance);

		accountRepo.save(fromAccount);
		accountRepo.save(toAccount);

		Transactions transaction = new Transactions(fromAcctno, toAcctno, ifsc);
		transactionRepo.save(transaction);

		return "Transaction id: " + transaction.getTransactionId() + ".\nTransfer successfully completed from :"
				+ fromAcctno + " ->to AccountNo :" + toAcctno;
	}

}
