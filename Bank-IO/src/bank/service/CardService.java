package bank.service;

import java.util.List;

import bank.entity.BankAccount;
import bank.entity.Card;

public interface CardService {
	public void addCard(BankAccount account);
	public List<Card> getCards(BankAccount account);
	public Card getCard(int id);
	public void saveCard(Card card);
}
