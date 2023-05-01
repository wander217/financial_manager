package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public abstract class GenericDAO<T> {	
	protected Connection con;
	
	public GenericDAO() throws SQLException, ClassNotFoundException {
		String url = GenericDAO.fromMSSQLConfig();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		this.con = DriverManager.getConnection(url);
	}
	
	// Mở kết nối
	public static String fromMSSQLConfig() {
		String url = "jdbc:sqlserver://127.0.0.1:1433;";
		url= url+"user="+MSSQLServerConfig.USERNAME+";";
		url= url+"password="+MSSQLServerConfig.PASSWORD+";";
		url= url+"encrypt=true;trustServerCertificate=true;";
		return url;
	}
	
	// Chuyển dữ liệu từ bảng thành object
	public abstract T process(ResultSet rs) throws SQLException;
	
	// Truyền attribute của object vào câu lệnh thông qua dấu ?
	public abstract PreparedStatement binding(PreparedStatement statement, T item) throws SQLException;
	
	//Lấy dữ liệu từ bảng
	public ArrayList<T> select(String query) throws SQLException{
		ArrayList<T> rs = new ArrayList<T>();
		Statement statement = this.con.createStatement();
		ResultSet table = statement.executeQuery(query);
		while(table.next()) {
			rs.add(this.process(table));
		}
		return rs;
	}
	
	//Xóa dữ liệu khỏi bảng
	public void delete(String query, int id) throws SQLException {
		PreparedStatement statement = this.con.prepareStatement(query);
		statement.setInt(1, id);
		statement.execute();
	}
	
	// Thêm dữ liệu vào bảng
	public void insert(String query, T item) throws SQLException {
		PreparedStatement statement = this.con.prepareStatement(query);
		statement = this.binding(statement, item);
		statement.execute();
	}
	
	//Đóng kết nối
	public void close() throws SQLException {
		if (this.con != null) {
			this.con.close();
		}
	}
	
	//Tạo database mới
	public void createDatabase(String dbName) throws SQLException {
		String query = "CREATE DATABASE " + dbName;
		PreparedStatement statement = this.con.prepareStatement(query);
		statement.execute();
	}
	
	//Tạo table mới
	public void createTable(String query) throws SQLException {
		PreparedStatement statement = this.con.prepareStatement(query);
		statement.execute();
	}
}

