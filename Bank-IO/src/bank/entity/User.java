package bank.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="login")
	private String login;
	
	@Column(name="password")
	private String password;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private UserType type;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="userdata_id")
	private UserData userData;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private List<BankAccount> bankAccounts;
	

	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}



	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}



	public void addBankAccounts(BankAccount bankAccounts) {
		if(this.bankAccounts.isEmpty()) {
			this.bankAccounts = new ArrayList<BankAccount>();
		}
		
		this.bankAccounts.add(bankAccounts);
	}


	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {}

	public User(String login, String password, UserType type) {
		super();
		this.login = login;
		this.password = password;
		this.type = type;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", type=" + type + "]";
	}
	
	
}
