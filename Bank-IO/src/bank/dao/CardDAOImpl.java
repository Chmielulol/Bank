package bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bank.entity.BankAccount;
import bank.entity.Card;

@Repository
public class CardDAOImpl implements CardDAO {
	
	@Autowired
	SessionFactory factory;

	@Override
	public List<Card> getCards(BankAccount account) {
		
		
		Session session = factory.getCurrentSession();
		
		List<Card> cards = session.createQuery("from Card where account_id="+account.getAccountNumber(),Card.class).getResultList();
		
		if(cards.isEmpty()) {
			cards=null;
		}
		
		return cards;
	}

	@Override
	public void saveCard(Card card) {
		
		Session session = factory.getCurrentSession();
		
		session.saveOrUpdate(card);

	}

	@Override
	public Card getCard(int id) {
		
		Session session = factory.getCurrentSession();
		
		return session.get(Card.class, id);
	}

}
