package bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bank.entity.Transfer;
import bank.entity.User;

@Repository
public class TransferDAOImpl implements TransferDAO {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public void saveTransfer(Transfer transfer) {
		
			Session session =factory.getCurrentSession();
			
			session.saveOrUpdate(transfer);
	}

	@Override
	public List<Transfer> getTransferHistory(User user) {
		
		Session session = factory.getCurrentSession();
		
		List<Transfer> transfers;
		
		transfers = session.createQuery("from Transfer where senderId='"+user.getId()+"' or recieverId='"+user.getId()+"' order by date", Transfer.class).getResultList();
		
		return transfers;
	}

}
