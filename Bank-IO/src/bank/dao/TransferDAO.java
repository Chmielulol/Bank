package bank.dao;

import java.util.List;

import bank.entity.Transfer;
import bank.entity.User;

public interface TransferDAO {
	public void saveTransfer(Transfer transfer);
	
	public List<Transfer> getTransferHistory(User user);
}
