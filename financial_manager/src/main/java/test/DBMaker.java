package test;
import dao.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.*;

public class DBMaker {
	public static void createSchema() {
		try {
			CardInfoDAO cardInfoDAO = new CardInfoDAO();
			
			//Khởi tạo database
			cardInfoDAO.createDatabase(MSSQLServerConfig.DATABASE);
			System.out.println("Tạo DB thành công");
			
			//Khởi tạo bảng bank
			String bankTableQuery = "CREATE TABLE "+MSSQLServerConfig.DATABASE+".dbo.bank("
					+ "id INT,"
					+ "bank_name NCHAR(1000),"
					+ "PRIMARY KEY (id),"
					+ ")";
			cardInfoDAO.createTable(bankTableQuery);
			System.out.println("Tạo bảng bank thành công");
			
			//Khởi tạo bảng card type
			String cardTypeTableQuery = "CREATE TABLE "+MSSQLServerConfig.DATABASE+".dbo.card_type("
					+ "id INT,"
					+ "card_type NCHAR(1000),"
					+ "PRIMARY KEY (id),"
					+ ")";
			cardInfoDAO.createTable(cardTypeTableQuery);
			System.out.println("Tạo bảng card type thành công");
			
			//Khởi tạo bảng card info
			String cardInfoTableQuery = "CREATE TABLE "+MSSQLServerConfig.DATABASE+".dbo.card_info("
					+ "id INT,"
					+ "card_name NCHAR(1000),"
					+ "card_type INT,"
					+ "bank INT,"
					+ "upper_limit DECIMAL(18,0),"
					+ "lower_limit DECIMAL(18,0),"
					+ "img_url NCHAR(1000),"
					+ "income DECIMAL(18,0),"
					+ "PRIMARY KEY (id),"
					+ ")";
			cardInfoDAO.createTable(cardInfoTableQuery);
			System.out.println("Tạo bảng card info thành công");
			cardInfoDAO.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void bankTableAdding() {
		try {
			// tạo bank DAO để thực hiện truy cập bảng Bank
			BankDAO bankDAO = new BankDAO();
			
			// lấy all dữ liệu từ bảng bank
			ArrayList<Bank> banks = bankDAO.getAll();
			for(Bank bank:banks){
				System.out.println(bank.getId());
				System.out.println(bank.getBankName());
			}
			
			// xóa dữ liệu cũ
			for(Bank bank:banks){
				Boolean rs = bankDAO.delete(bank.getId());
				if(rs) {
					System.out.println("Del "+ Integer.toString(bank.getId())+" sucess");
				} else {
					System.out.println("Eror with "+ Integer.toString(bank.getId()));
				}
			}
			
			// đọc thông tin từ file
			File file = new File("src\\main\\java\\test\\data\\bank_name.txt");
			Scanner scanner = new Scanner(file);
			
			// thêm dữ liệu mới vào bảng
			while(scanner.hasNext()) {
				//đọc từng dòng
				String data = scanner.nextLine();
				//phân chia ra theo dấu ,
				String[] row = data.split(",");
				
				//đóng gói dữ liệu
				Bank bank = new Bank();
				bank.setId(Integer.parseInt(row[0]));
				bank.setBankName(row[1]);
				
				//thêm mới vào bảng
				Boolean rs = bankDAO.insert(bank);
				if(rs) {
					System.out.println("Add "+ Integer.toString(bank.getId())+" sucess");
				}
			}
			bankDAO.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void cardTypeTableAdding() {
		try {
			// tạo bank DAO để thực hiện truy cập bảng Bank
			CardTypeDAO cardTypeDAO = new CardTypeDAO();
			
			// lấy all dữ liệu từ bảng bank
			ArrayList<CardType> cardTypes = cardTypeDAO.getAll();
			for(CardType cardType:cardTypes){
				System.out.println(cardType.getId());
				System.out.println(cardType.getCardTypeName());
			}
			
			// xóa dữ liệu cũ
			for(CardType cardType:cardTypes){
				Boolean rs = cardTypeDAO.delete(cardType.getId());
				if(rs) {
					System.out.println("Del "+ Integer.toString(cardType.getId())+" sucess");
				} else {
					System.out.println("Eror with "+ Integer.toString(cardType.getId()));
				}
			}
			
			// đọc thông tin từ file
			File file = new File("src\\main\\java\\test\\data\\card_type.txt");
			Scanner scanner = new Scanner(file);
			
			// thêm dữ liệu mới vào bảng
			while(scanner.hasNext()) {
				//đọc từng dòng
				String data = scanner.nextLine();
				//phân chia ra theo dấu ,
				String[] row = data.split(",");
				
				//đóng gói dữ liệu
				CardType cardType = new CardType();
				cardType.setId(Integer.parseInt(row[0]));
				cardType.setCardTypeName(row[1]);
				
				//thêm mới vào bảng
				Boolean rs = cardTypeDAO.insert(cardType);
				if(rs) {
					System.out.println("Add "+ Integer.toString(cardType.getId())+" sucess");
				}
			}
			
			cardTypeDAO.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void cardInfoTableAdding() {
		try {
			// tạo DAO để thực hiện truy cập bảng Card Info
			CardInfoDAO CardInfoDAO = new CardInfoDAO();
			
			// lấy all dữ liệu từ bảng bank
			ArrayList<CardInfo> cardInfos = CardInfoDAO.getAll();
			for(CardInfo cardInfo:cardInfos){
				System.out.println(cardInfo.getId());
				System.out.println(cardInfo.getCardName());
			}
			
			// xóa dữ liệu cũ
			for(CardInfo cardInfo:cardInfos){
				Boolean rs = CardInfoDAO.delete(cardInfo.getId());
				if(rs) {
					System.out.println("Del "+ Integer.toString(cardInfo.getId())+" sucess");
				} else {
					System.out.println("Eror with "+ Integer.toString(cardInfo.getId()));
				}
			}
			
			// đọc thông tin từ file
			File file = new File("src\\main\\java\\test\\data\\card_info.txt");
			Scanner scanner = new Scanner(file);
			
			// thêm dữ liệu mới vào bảng
			while(scanner.hasNext()) {
				//đọc từng dòng
				String data = scanner.nextLine();
				//phân chia ra theo dấu ,
				String[] row = data.split(",");
				
				//đóng gói dữ liệu
				CardInfo cardInfo = new CardInfo();
				cardInfo.setId(Integer.parseInt(row[0]));
				Bank bank = new Bank();
				bank.setId(Integer.parseInt(row[1]));
				cardInfo.setBank(bank);
				CardType cardType = new CardType();
				cardType.setId(Integer.parseInt(row[2]));
				cardInfo.setCardType(cardType);
				cardInfo.setCardName(row[3]);
				cardInfo.setLowerLimit(Float.parseFloat(row[4]));
				cardInfo.setUpperLimit(Float.parseFloat(row[5]));
				cardInfo.setIncome(Float.parseFloat(row[6]));
				cardInfo.setImgURL(row[7]);
				
				//thêm mới vào bảng
				Boolean rs = CardInfoDAO.insert(cardInfo);
				if(rs) {
					System.out.println("Add "+ Integer.toString(cardInfo.getId())+" sucess");
				}
			}
			CardInfoDAO.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		// Tạo Database mới
		createSchema();
		
		// Thêm dữ liệu vào bảng bank
		bankTableAdding();
		
		// Thêm dữ liệu vào bảng card type
		cardTypeTableAdding();
		
		//Thêm dữ liệu vào bảng card info
		cardInfoTableAdding();
	}

}
