package model;

public class CardInfoFilter {
	private int bankId;
	private int cardTypeId;
	private double income;
	
	public CardInfoFilter() {
		// TODO Auto-generated constructor stub
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public int getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(int cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}
	
}
