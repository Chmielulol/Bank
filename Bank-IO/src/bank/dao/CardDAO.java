package bank.dao;

import java.util.List;

import bank.entity.BankAccount;
import bank.entity.Card;

public interface CardDAO {

	public List<Card> getCards(BankAccount account);
	public void saveCard(Card card);
	public Card getCard(int id);
}
