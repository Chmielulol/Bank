package bank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="card")
public class Card {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="expiery_date")
	private String expDate;
	
	@Column(name="security_code")
	private int secCode;
	
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST}, fetch=FetchType.LAZY)
	private BankAccount account;
	
	@Column(name="card_limit")
	private double cardLimit;
	
	@Column(name="pin")
	private int pin;
	
	public Card() {}
	
	

	public Card(String expDate, int secCode, BankAccount account, double cardLimit,int pin) {
		super();
		this.expDate = expDate;
		this.secCode = secCode;
		this.account = account;
		this.cardLimit = cardLimit;
		this.pin=pin;
	}

	

	public int getPin() {
		return pin;
	}



	public void setPin(int pin) {
		this.pin = pin;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public int getSecCode() {
		return secCode;
	}

	public void setSecCode(int secCode) {
		this.secCode = secCode;
	}

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}

	public double getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}
	
	
}
