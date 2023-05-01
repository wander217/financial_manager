package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Bank;
import model.CardInfo;
import model.CardInfoFilter;
import model.CardType;

public class CardInfoDAO extends GenericDAO<CardInfo>{
	private String tableName = MSSQLServerConfig.DATABASE+".dbo.card_info";
	private String selectAllQuery = "SELECT * FROM "+tableName;
	private String deleteQuery = "DELETE FROM "+tableName+" WHERE id = ?";
	private String insertQuery = "INSERT INTO "+tableName+" VALUES(? , ?, ?, ? , ?, ?, ?, ?)";
	private String filterQuery = "SELECT * FROM "+tableName+" WHERE bank=? AND card_type=? AND income<=?";
	
	
	public CardInfoDAO() throws SQLException, ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public CardInfo process(ResultSet rs) throws SQLException {
		CardInfo cardInfo = new CardInfo();
		cardInfo.setId(rs.getInt("id"));
		cardInfo.setCardName(rs.getString("card_name"));
		cardInfo.setUpperLimit(rs.getFloat("upper_limit"));
		cardInfo.setLowerLimit(rs.getFloat("lower_limit"));
		cardInfo.setIncome(rs.getFloat("income"));
		cardInfo.setImgURL(rs.getString("img_url"));
		
		Bank bank = new Bank();
		bank.setId(rs.getInt("bank"));
		cardInfo.setBank(bank);
		
		CardType cardType = new CardType();
		cardType.setId(rs.getInt("card_type"));
		cardInfo.setCardType(cardType);
		return cardInfo;
	}


	@Override
	public PreparedStatement binding(PreparedStatement statement, CardInfo item) throws SQLException {
		statement.setInt(1, item.getId());
		statement.setString(2, item.getCardName());
		statement.setInt(3, item.getCardType().getId());
		statement.setInt(4, item.getBank().getId());
		statement.setDouble(5, item.getUpperLimit());
		statement.setDouble(6, item.getLowerLimit());
		statement.setString(7, item.getImgURL());
		statement.setDouble(8, item.getIncome());
		return statement;
	}
	
	public ArrayList<CardInfo> getAll() {
		try {
			return this.select(this.selectAllQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<CardInfo>();
		}
	}
	
	public Boolean delete(int id) {
		try {
			super.delete(this.deleteQuery, id);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean insert(CardInfo cardInfo) {
		try {
			super.insert(this.insertQuery, cardInfo);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<CardInfo> filter(CardInfoFilter cardInfoFitler) {
		try {
			ArrayList<CardInfo> rs = new ArrayList<CardInfo>();
			PreparedStatement statement = this.con.prepareStatement(this.filterQuery);
			statement.setInt(1, cardInfoFitler.getBankId());
			statement.setInt(2, cardInfoFitler.getCardTypeId());
			statement.setDouble(3, cardInfoFitler.getIncome());
			ResultSet table = statement.executeQuery();
			while(table.next()) {
				rs.add(this.process(table));
			}
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<CardInfo>();
		}
	}
}
