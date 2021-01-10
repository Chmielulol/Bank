package bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transfer_history")
public class Transfer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="sender_id")
	private int senderId;
	
	@Column(name="reciever_id")
	private int recieverId;
	
	@Column(name="sender_account_id")
	private int senderAccountId;
	
	@Column(name="reciever_account_id")
	private int recieverAccountId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="date")
	private String date;
	
	@Column(name="money")
	private double money;

	@Column(name="currency")
	@Enumerated(EnumType.STRING)
	private CurrencyType currency;
	
	public Transfer() {}
	
	
	
	public Transfer(int senderId, int recieverId, int senderAccountId, int recieverAccountId, String title, String date,
			double money, CurrencyType currency) {
		super();
		this.senderId = senderId;
		this.recieverId = recieverId;
		this.senderAccountId = senderAccountId;
		this.recieverAccountId = recieverAccountId;
		this.title = title;
		this.date = date;
		this.money = money;
		this.currency = currency;
	}


	public int getSenderAccountId() {
		return senderAccountId;
	}



	public void setSenderAccountId(int senderAccountId) {
		this.senderAccountId = senderAccountId;
	}



	public int getRecieverAccountId() {
		return recieverAccountId;
	}



	public void setRecieverAccountId(int recieverAccountId) {
		this.recieverAccountId = recieverAccountId;
	}



	public CurrencyType getCurrency() {
		return currency;
	}


	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(int recieverId) {
		this.recieverId = recieverId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}



	@Override
	public String toString() {
		return "Transfer [id=" + id + ", senderId=" + senderId + ", recieverId=" + recieverId + ", senderAccountId="
				+ senderAccountId + ", recieverAccountId=" + recieverAccountId + ", title=" + title + ", date=" + date
				+ ", money=" + money + ", currency=" + currency + "]";
	}
	
	
}
