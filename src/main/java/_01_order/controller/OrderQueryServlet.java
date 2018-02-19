package _01_order.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _01_order.model.Orders;
import _01_order.model.dao.OrdersDao;
import _01_order.model.dao.OrdersDaoImpl;

@WebServlet("/OrderQueryServlet.do")
public class OrderQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
       
    public OrderQueryServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();

		// 解析Web端送來之查詢訂單請求資訊
		// 尚未處理，查無訂單之回應
//		String reviewFood = request.getParameter("review_food");
		Integer order_id = Integer.parseInt(request.getParameter("order_id"));
		OrdersDao ordersDao = new OrdersDaoImpl();
		Orders orderBean = ordersDao.getOrderById(order_id);
		System.out.println("查詢訂單資訊: " + orderBean.toString());
		String orderJson = gson.toJson(orderBean); // Object to JSON
		System.out.println("查詢訂單資訊JSON: " + orderJson);
		writeText(response, orderJson);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
	

}
