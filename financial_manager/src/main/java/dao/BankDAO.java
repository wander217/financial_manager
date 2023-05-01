package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Bank;

public class BankDAO extends GenericDAO<Bank>{
	private String tableName = MSSQLServerConfig.DATABASE+".dbo.bank";
	private String selectAllQuery = "SELECT * FROM "+tableName;
	private String deleteQuery = "DELETE FROM "+tableName+" WHERE id = ?";
	private String insertQuery = "INSERT INTO "+tableName+" VALUES(? , ?)";

	public BankDAO() throws SQLException, ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bank process(ResultSet rs) throws SQLException {
		Bank bank = new Bank();
		bank.setId(rs.getInt("id"));
		bank.setBankName(rs.getString("bank_name"));
		return bank;
	}
	
	@Override
	public PreparedStatement binding(PreparedStatement statement, Bank item) throws SQLException {
		statement.setInt(1, item.getId());
		statement.setString(2, item.getBankName());
		return statement;
	}
	
	public ArrayList<Bank> getAll() {
		try {
			ArrayList<Bank> rs = this.select(this.selectAllQuery);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Bank>();
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
	
	public Boolean insert(Bank bank) {
		try {
			super.insert(this.insertQuery, bank);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
