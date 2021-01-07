package podzielony_bank_2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bank_account")
public class BankAccount{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int accountNumber;
	
	@Column(name="percentage")
	private double percentage;
	
	@Column(name="money")
	private double money;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST}, fetch=FetchType.LAZY)
	private User user;

	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private AccountType type;
	
	@Column(name="currency")
	@Enumerated(EnumType.STRING)
	private CurrencyType currency;
	
	@Column(name="transaction_limit")
	private double limit;
	
	public BankAccount() {}
	
	public BankAccount(double percentage, String name, User user, AccountType type, CurrencyType currency, double limit) {
		super();
		this.percentage = percentage;
		this.name = name;
		this.user = user;
		this.type = type;
		this.currency = currency;
		this.limit = limit;
	}
	

	public double getLimit() {
		return limit;
	}



	public void setLimit(double limit) {
		this.limit = limit;
	}



	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}


	public CurrencyType getCurrency() {
		return currency;
	}



	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}



	public void setName(String name) {
		this.name = name;
	}




	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
