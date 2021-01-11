package bank.service;

import java.util.List;

import bank.entity.BankAccount;
import bank.entity.Transfer;
import bank.entity.User;

public interface TransferService {

	public boolean makeTransfer(BankAccount sender, BankAccount reciever,String title, double money);
	
	public List<Transfer> getTransferHisory(User user);
	
	public boolean isValid(Transfer transfer);
	
}
