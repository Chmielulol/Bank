package bank.service;

import bank.entity.BankAccount;
import bank.entity.User;

public interface AccountService {

	public User getAccounts(User user);
	public BankAccount getAccount(int theId);
	public void saveAccount(BankAccount account);
}
