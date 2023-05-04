<%@page import="model.CardInfo"%>
<%@page import="model.Bank"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.CardType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Thông tin các loại thẻ</title>
	<link rel="stylesheet" href="./generic/css/font.css">
	<link rel="stylesheet" href="./card_info/style.css">
</head>
<body>
	<div class="container">
		<div class="nav">
			<div class="nav__logo">
				<img src="generic/image/logo.jpg">
				<a href="#">Logo</a>
			</div>
			
			<div class="nav__link">
				<a href="#" class="nav__link__item">Tài khoản ngân hàng</a>
				<a href="#" class="nav__link__item">Thẻ tín dụng</a>
				<a href="#" class="nav__link__item">Vay cá nhân</a>
			</div>
			
			<div class="nav__button">
				<a href="#">Đăng kí</a>
			</div>
		</div>
		
		<div class="main">
			<div class="main__sidebar">
				<form class="filter" action="<%=request.getContextPath()%>/card_info" method="POST">
					<p class="filter__content">Bộ lọc tìm kiếm</p>
					<div class="filter__main">
						<p class="filter__main__label">Loại thẻ tín dụng:</p>
						<select class="filter__main__option" name="cardtype">
							<% Integer card_type_id = (Integer)request.getSession().getAttribute("selected_card_type"); %>
						    <% ArrayList<CardType> cardTypes = (ArrayList<CardType>)request.getSession().getAttribute("cardTypes"); %>
							<% for(CardType cardType: cardTypes){ %>
							 <% if(card_type_id == cardType.getId()){ %>
							 	<option  value="<%= cardType.getId()%>" selected="selected">
							 <%} else { %>
							  	<option value="<%= cardType.getId()%>">
							 <% } %>
								  	 <%=cardType.getCardTypeName() %>
								</option>
							 <%} %>
						 </select>
					</div>
					
					<div class="filter__main">
						<p class="filter__main__label">Ngân hàng:</p>
						<select class="filter__main__option" name="bank" >
						    <% ArrayList<Bank> banks = (ArrayList<Bank>)request.getSession().getAttribute("banks"); %>
						    <% Integer bank_id = (Integer)request.getSession().getAttribute("selected_bank"); %>
							<% for(Bank bank: banks){ %>
								<% if(bank_id == bank.getId()){ %>
								 <option  value="<%= bank.getId()%>" selected="selected">
								<%} else { %>
								  <option value="<%= bank.getId()%>">
							     <% } %>
								  	 <%=bank.getBankName()%>
								  </option>
							 <%} %>
						 </select>
					</div>
					
					<div class="filter__main">
					    <% String income = (String)request.getSession().getAttribute("income"); %>
						<p class="filter__main__label">Thu nhập tối thiểu (triệu VNĐ):</p>
						<input class="filter__main__option" type="number" name="lower_limit" min=0 placeholder="Nhập thu nhập" step=".01" value="<%=income%>">
					</div>
					
					<div class="filter__button">
						<button type="submit" class="filter__button__submit">Tìm kiếm</button>
						<button type="reset" class="filter__button__reset">Đặt lại</button>
					</div>
				</form>
			</div>
			
			<div class="main__showroom">
				<% ArrayList<CardInfo> cardInfos = (ArrayList<CardInfo>)request.getSession().getAttribute("cardInfos"); %>
				<% for(CardInfo cardInfo: cardInfos){ %>
				  <div class="main__showroom__card">
					<img src="generic/image/card/<%=cardInfo.getImgURL().trim()%>" class="main__showroom_card__image" alt="card image">
					<p class="main__showroom_card__name"><%=cardInfo.getCardName().trim()%></p>
					<p class="main__showroom_card__info"><b>Ngân hàng:</b><%=cardInfo.getBank().getBankName().trim()%></p>
					<p class="main__showroom_card__info"><b>Loại thẻ:</b><%=cardInfo.getCardType().getCardTypeName().trim()%></p>
					<% if(cardInfo.getUpperLimit() >= 1e12){ %>
						<p class="main__showroom_card__info"><b>Hạn mức:</b><%=String.format("%.2f",cardInfo.getLowerLimit()/1e6)%> triệu trở lên</p>
					<% } else if (cardInfo.getUpperLimit() >= 1e9){ %>
						<p class="main__showroom_card__info"><b>Hạn mức:</b><%=String.format("%.2f",cardInfo.getLowerLimit()/1e6)%> triệu - <%=String.format("%.2f",cardInfo.getUpperLimit()/1e9)%> tỷ</p>
					<%} else { %>
						<p class="main__showroom_card__info"><b>Hạn mức:</b><%=String.format("%.2f",cardInfo.getLowerLimit()/1e6)%> triệu - <%=String.format("%.2f",cardInfo.getUpperLimit()/1e6)%> triệu</p>
					<% } %>
					<p class="main__showroom_card__info"><b>Thu nhập:</b>>=<%=String.format("%.2f",cardInfo.getIncome()/1e6)%> triệu</p>
				  </div>
				 <%} %>
			</div>
		</div>
		
		<div class="footer">
			<div class="footer__link">
			</div>
			
			<div class="footer__">
			</div>
			
			<div class="footer___">
			</div>
		</div>
	</div>
</body>
</html>