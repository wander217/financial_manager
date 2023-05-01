package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bank;
import model.CardInfo;
import model.CardInfoFilter;
import model.CardType;
import service.CardInfoService;

/**
 * Servlet implementation class CardInfoServlet
 */
@WebServlet("/card_info")
public class CardInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CardInfoService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardInfoServlet() {
        super();
        try {
			this.service = new CardInfoService();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// In ra page của module card_info
		if(this.service != null) {
			//Truy cập vào session
			HttpSession session = request.getSession();
			
			//Set thuộc tính mặc định tìm kiếm
			session.setAttribute("selected_card_type", 0);
			session.setAttribute("selected_bank", 0);
			session.setAttribute("income", "0.00");
			
			//Kiếm tra session đã có thuộc tính chưa
			ArrayList<CardType> cardTypes = (ArrayList<CardType>)session.getAttribute("cardTypes");
			//Nếu chưa có thì load từ database lên và thêm vào session
			if(cardTypes == null) {
				//load từ database card type lên
				cardTypes = this.service.getAllCardType();
				//Truyền dữ liệu vào session
				session.setAttribute("cardTypes", cardTypes);
			}
			
			//tương tự với bank
			ArrayList<Bank> banks = (ArrayList<Bank>)session.getAttribute("banks");
			if(banks == null) {
				banks = this.service.getAllBank();
				session.setAttribute("banks", banks);
			}
			
			//tương tự với card info
			ArrayList<CardInfo> cardInfos = this.service.getAllCardInfo(cardTypes, banks);
			session.setAttribute("cardInfos", cardInfos);
		}
		
		//Thực hiện mở file jsp và hiển thị
		request.getRequestDispatcher("./card_info/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Lấy dữ liệu truyền lên
		String cardType = (String)request.getParameter("cardtype");
		String bankName = (String)request.getParameter("bank");
		String income = (String)request.getParameter("lower_limit");
		
		CardInfoFilter cardInfofilter = new CardInfoFilter();
		cardInfofilter.setBankId(Integer.parseInt(bankName));
		cardInfofilter.setCardTypeId(Integer.parseInt(cardType));
		cardInfofilter.setIncome(Double.parseDouble(income)* 1000000);
		
		if(this.service != null) {
			//Truy cập vào session
			HttpSession session = request.getSession();
			
			//Set thuộc tính mặc định tìm kiếm
			session.setAttribute("selected_card_type", cardInfofilter.getCardTypeId());
			session.setAttribute("selected_bank", cardInfofilter.getBankId());
			session.setAttribute("income", String.format("%.2f",cardInfofilter.getIncome()/1000000));
			
			//Kiếm tra session đã có thuộc tính chưa
			ArrayList<CardType> cardTypes = (ArrayList<CardType>)session.getAttribute("cardTypes");
			//Nếu chưa có thì load từ database lên và thêm vào session
			if(cardTypes == null) {
				//load từ database card type lên
				cardTypes = this.service.getAllCardType();
				//Truyền dữ liệu vào session
				session.setAttribute("cardTypes", cardTypes);
			}
			
			//tương tự với bank
			ArrayList<Bank> banks = (ArrayList<Bank>)session.getAttribute("banks");
			if(banks == null) {
				banks = this.service.getAllBank();
				session.setAttribute("banks", banks);
			}
			
			//lọc thông tin và in ra
			ArrayList<CardInfo> cardInfos = this.service.filter(cardTypes, banks, cardInfofilter);
			session.setAttribute("cardInfos", cardInfos);
		}
		
		//Thực hiện mở file jsp và hiển thị
		request.getRequestDispatcher("./card_info/index.jsp").forward(request, response);
	}

}
