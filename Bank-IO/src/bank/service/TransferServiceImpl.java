package bank.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.dao.AccountDAO;
import bank.dao.TransferDAO;
import bank.entity.BankAccount;
import bank.entity.Transfer;
import bank.entity.User;

@Service
public class TransferServiceImpl implements TransferService {

	
	@Autowired
	private TransferDAO transferDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	@Transactional
	public boolean makeTransfer(BankAccount sender, BankAccount reciever,String title, double money) {
		if(sender.getCurrency()==reciever.getCurrency() && sender.getMoney() >= money && sender.getLimit()>=money) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date=new Date();
			Transfer transfer = new Transfer(sender.getUser().getId(),reciever.getUser().getId(),sender.getAccountNumber(), reciever.getAccountNumber(),title,formatter.format(date),money,sender.getCurrency());
			sender.setMoney(sender.getMoney()-money);
			reciever.setMoney(reciever.getMoney()+money);
			accountDAO.saveAccount(sender);
			accountDAO.saveAccount(reciever);
			transferDAO.saveTransfer(transfer);
			return true;
		}
		else 
			return false;
	}

	@Override
	@Transactional
	public List<Transfer> getTransferHisory(User user) {
		
		return transferDAO.getTransferHistory(user);
		
	}

	@Override
	@Transactional
	public boolean isValid(Transfer transfer) {
		
		if(accountDAO.getAccount(transfer.getRecieverAccountId())==null || transfer.getRecieverAccountId()==transfer.getSenderAccountId()) {
			return false;
		}
		
		if(transfer.getMoney() <=0) {
			return false;
		}
		if(transfer.getTitle()==null) {
			return false;
		}
		
		return true;
	}

}
