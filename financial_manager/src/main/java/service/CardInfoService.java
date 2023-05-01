package service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import dao.BankDAO;
import dao.CardTypeDAO;
import model.Bank;
import model.CardInfo;
import model.CardInfoFilter;
import model.CardType;
import dao.CardInfoDAO;

public class CardInfoService {
	private BankDAO bankDAO;
	private CardTypeDAO cardTypeDAO;
	private CardInfoDAO cardInfoDAO;
	
	public CardInfoService() throws SQLException, ClassNotFoundException {
		this.bankDAO = new BankDAO();
		this.cardTypeDAO = new CardTypeDAO();
		this.cardInfoDAO = new CardInfoDAO();
	}
	
	public ArrayList<CardType> getAllCardType() {
		return this.cardTypeDAO.getAll();
	}
	
	public ArrayList<Bank> getAllBank(){
		return this.bankDAO.getAll();
	}
	
	public ArrayList<CardInfo> getAllCardInfo(ArrayList<CardType>  cardTypes, ArrayList<Bank>  banks){
		//Lấy all loại card
		HashMap<Integer, CardType> cardTypeMap = new HashMap<Integer, CardType>();
		for(CardType cardType:cardTypes) {
			cardTypeMap.put(cardType.getId(), cardType);
		}
		//Lấy all bank
		HashMap<Integer, Bank> bankMap = new HashMap<Integer, Bank>();
		for(Bank bank:banks) {
			bankMap.put(bank.getId(), bank);
		}
		//Lấy all thẻ
		ArrayList<CardInfo> cardInfos = this.cardInfoDAO.getAll();
		
		// Map lại dữ liệu
		for(CardInfo cardInfo:cardInfos) {
			cardInfo.setBank(bankMap.get(cardInfo.getBank().getId()));
			cardInfo.setCardType(cardTypeMap.get(cardInfo.getCardType().getId()));
		}
		
		//Trả lại dữ liệu
		return cardInfos;
	}
	
	public ArrayList<CardInfo> filter(ArrayList<CardType>  cardTypes, ArrayList<Bank>  banks, CardInfoFilter cardInfoFilter){
		//Lấy all loại card
		HashMap<Integer, CardType> cardTypeMap = new HashMap<Integer, CardType>();
		for(CardType cardType:cardTypes) {
			cardTypeMap.put(cardType.getId(), cardType);
		}
		//Lấy all bank
		HashMap<Integer, Bank> bankMap = new HashMap<Integer, Bank>();
		for(Bank bank:banks) {
			bankMap.put(bank.getId(), bank);
		}
		//Lấy all thẻ
		ArrayList<CardInfo> cardInfos = this.cardInfoDAO.filter(cardInfoFilter);
		
		// Map lại dữ liệu
		for(CardInfo cardInfo:cardInfos) {
			cardInfo.setBank(bankMap.get(cardInfo.getBank().getId()));
			cardInfo.setCardType(cardTypeMap.get(cardInfo.getCardType().getId()));
		}
		
		//Trả lại dữ liệu
		return cardInfos;
	}
}
