package _01_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _01_order.model.ORDERS;
import _01_order.model.dao.OrdersDao;
import _01_order.model.dao.OrdersDaoImpl;

@WebServlet("/OrderQueryByStore.do")
public class OrderQueryByStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE_1 = "text/html; charset=UTF-8";
	private static final String CONTENT_TYPE_2 = "application/json; charset=UTF-8";
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();

		// 以下為接收App端送來查詢訂單請求資訊準備(json格式)，未完成
//		BufferedReader br = request.getReader();
//		StringBuilder insertOrderJson = new StringBuilder();
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			insertOrderJson.append(line);
//		}
//		System.out.println("input: " + insertOrderJson);
//		JsonObject insertOrderJsonObj = gson.fromJson(insertOrderJson.toString(),
//				 JsonObject.class); // 轉為json物件
//		String action = insertOrderJsonObj.get("action").getAsString();
//		if (action.equals("getOrdersList")) {
//			OrdersDao ordersDao = new OrdersDaoImpl();
//			List<Orders> orderList = ordersDao.getOrderList();
//			System.out.println("查詢訂單資訊: " + orderList.toString());
//		}
		
		OrdersDao ordersDao = new OrdersDaoImpl();
		Integer orderId = Integer.parseInt(request.getParameter("store_id"));
		List<ORDERS> ordersByStore = ordersDao.getOrdersDetailByStore(orderId);
		System.out.println("使用者訂單明細:" + ordersByStore.toString());
		String ordersByOrderIdJson = gson.toJson(ordersByStore); // Object to JSON
		writeText(response, ordersByOrderIdJson);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE_2);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
