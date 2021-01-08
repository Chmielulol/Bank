package bank.dao;

import java.util.List;

import bank.entity.BankAccount;
import bank.entity.User;

public interface AccountDAO {
	public List<BankAccount> getAccounts(User user);
	public void saveAccount(BankAccount account);
	public BankAccount getAccount(int theId);
}
