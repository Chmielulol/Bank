package bank.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.dao.CardDAO;
import bank.entity.AccountType;
import bank.entity.BankAccount;
import bank.entity.Card;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	CardDAO cardDAO;

	@Override
	@Transactional
	public void addCard(BankAccount account) {
		
		if(account != null && account.getType() == AccountType.Current) {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.YEAR, 3);
			Date expDate = c.getTime();
			
			String expDateString = format.format(expDate);
			
			Random rand = new Random();
			
			int secCode = rand.nextInt(900) +100;
			
			int pin = rand.nextInt(9000)+1000;
			
			Card card = new Card(expDateString,secCode,account,1000,pin);
			
			cardDAO.saveCard(card);
			
		}
		
	}

	@Override
	@Transactional
	public List<Card> getCards(BankAccount account) {
		// TODO Auto-generated method stub
		return cardDAO.getCards(account);
	}

	@Override
	@Transactional
	public Card getCard(int id) {
		// TODO Auto-generated method stub
		return cardDAO.getCard(id);
	}

	@Override
	@Transactional
	public void saveCard(Card card) {
		cardDAO.saveCard(card);
		
	}

}
