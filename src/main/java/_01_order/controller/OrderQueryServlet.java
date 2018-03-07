package _01_order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _01_order.model.ORDERS;
import _01_order.model.dao.OrdersDao;
import _01_order.model.dao.OrdersDaoImpl;

@WebServlet("/OrderQueryServlet.do")
public class OrderQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE_1 = "text/html; charset=UTF-8";
	private static final String CONTENT_TYPE_2 = "application/json; charset=UTF-8";

	public OrderQueryServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		// 解析Web端送來之查詢訂單請求資訊
		// 尚未處理，查無訂單之回應
		// String reviewFood = request.getParameter("review_food");
		Integer userId = Integer.parseInt(request.getParameter("user_id"));
//		Integer storeId = Integer.parseInt(request.getParameter("store_id"));
		List<ORDERS> ordersByUser = ordersDao.getOrdersDetailByUser(userId);
//		List<ORDERS> ordersBystore = ordersDao.getOrdersDeatilByStore(storeId);
//		ordersDao.getOrdersDetailByUser(userId);
		System.out.println("使用者訂單明細:" + ordersByUser.toString());
		String ordersByUserJson = gson.toJson(ordersByUser); // Object to JSON
//		String ordersByStoreJson = gson.toJson(ordersBystore); // Object to JSON
		writeText(response, ordersByUserJson);
//		writeText(response, ordersByStoreJson);
		
//		Integer order_id = Integer.parseInt(request.getParameter("order_id"));
//		Orders orderBean = ordersDao.getOrderById(order_id);
//		System.out.println("查詢訂單資訊: " + orderBean.toString());
//		String orderJson = gson.toJson(orderBean); // Object to JSON
//		System.out.println("查詢訂單資訊JSON: " + orderJson);
//		writeText(response, orderJson);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE_2);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}

}
