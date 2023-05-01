package model;

import java.io.Serializable;

public class CardInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private Bank bank;
	private CardType cardType;
	private String cardName;
	private double lowerLimit;
	private double upperLimit;
	private double income;
	private String imgURL;
	
	public CardInfo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Bank getBank() {
		return bank;
	}



	public void setBank(Bank bank) {
		this.bank = bank;
	}



	public CardType getCardType() {
		return cardType;
	}



	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}



	public String getCardName() {
		return cardName;
	}



	public void setCardName(String cardName) {
		this.cardName = cardName;
	}



	public String getImgURL() {
		return imgURL;
	}



	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}



	public double getLowerLimit() {
		return lowerLimit;
	}



	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}



	public double getUpperLimit() {
		return upperLimit;
	}



	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}



	public double getIncome() {
		return income;
	}



	public void setIncome(double income) {
		this.income = income;
	}
}
