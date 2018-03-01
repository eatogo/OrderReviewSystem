package _01_order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _01_order.model.ORDERS;
import _01_order.model.dao.OrdersDao;
import _01_order.model.dao.OrdersDaoImpl;

@WebServlet("/OrderUpdateServlet.do")
public class OrderUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int order_id = 1;
	ORDERS order = null;
       
    public OrderUpdateServlet() {
        super();
        
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		OrdersDaoImpl ordersDao = new OrdersDaoImpl();
		ordersDao.updateOrderStatus_1(order);
		
		
	}

}
