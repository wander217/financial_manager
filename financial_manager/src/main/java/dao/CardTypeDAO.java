package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.CardType;

public class CardTypeDAO extends GenericDAO<CardType>{
	private String tableName = MSSQLServerConfig.DATABASE+".dbo.card_type";
	private String selectAllQuery = "SELECT * FROM "+tableName;
	private String deleteQuery = "DELETE FROM "+tableName+" WHERE id = ?";
	private String insertQuery = "INSERT INTO "+tableName+" VALUES(? , ?)";

	public CardTypeDAO() throws SQLException, ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public CardType process(ResultSet rs) throws SQLException {
		CardType cardType = new CardType();
		cardType.setId(rs.getInt("id"));
		cardType.setCardTypeName(rs.getString("card_type"));
		return cardType;
	}
	
	@Override
	public PreparedStatement binding(PreparedStatement statement, CardType item) throws SQLException {
		statement.setInt(1, item.getId());
		statement.setString(2, item.getCardTypeName());
		return statement;
	}
	
	public ArrayList<CardType> getAll() {
		try {
			ArrayList<CardType> rs = this.select(this.selectAllQuery);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<CardType>();
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
	
	public Boolean insert(CardType cardType) {
		try {
			super.insert(this.insertQuery, cardType);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
