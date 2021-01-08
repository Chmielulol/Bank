package podzielony_bank_2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bank.entity.BankAccount;
import bank.entity.User;

@Repository
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public List<BankAccount> getAccounts(User user) {
		Session session = factory.getCurrentSession();
		Query<BankAccount> query= session.createQuery("from BankAccount where user_id="+user.getId(),BankAccount.class);
		
		return query.getResultList();
	}

	@Override
	public void saveAccount(BankAccount account) {

		Session session = factory.getCurrentSession();
		
		session.saveOrUpdate(account);
	}

	@Override
	public BankAccount getAccount(int theId) {

		Session session = factory.getCurrentSession();
		
		return session.get(BankAccount.class, theId);
	}

}
