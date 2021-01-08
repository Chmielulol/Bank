package bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.dao.AccountDAO;
import bank.entity.BankAccount;
import bank.entity.User;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	@Transactional
	public User getAccounts(User user) {
		
		user.setBankAccounts(accountDAO.getAccounts(user));
		
		return user;
	}

	@Override
	@Transactional
	public BankAccount getAccount(int theId) {
		
		return accountDAO.getAccount(theId);
	}

	@Override
	@Transactional
	public void saveAccount(BankAccount account) {
		accountDAO.saveAccount(account);
		
	}


}
