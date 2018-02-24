package _01_order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _01_order.model.Order_details;
import _01_order.model.Orders;
import _01_order.model.dao.OrdersDao;
import _01_order.model.dao.OrdersDaoImpl;

@WebServlet("/OrderInsertServlet.do")
public class OrderInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	private Orders insertOrderInit;
	private Set <Order_details> details = new LinkedHashSet<>();
	
	public OrderInsertServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init();

		Integer order_user = 1;
		String order_note = "麵加大";
		Date order_time = new Date(System.currentTimeMillis());
		Date order_reserve_date = Date.valueOf("2018-02-02");
		Integer order_store = 1;
		Integer order_confirm_user = 1;
		Date order_confirm_time = new Date(System.currentTimeMillis());
		String order_takeout_period = "A";
		String order_status = "ordered";
		Date order_finished_time = new Date(System.currentTimeMillis());
		
		details.add(new Order_details(null, 1, 1,1));
		details.add(new Order_details(null, 1, 2, 2));
		details.add(new Order_details(null, 1, 3, 3));
			
		insertOrderInit = new Orders(null, order_user, order_note, order_time, order_reserve_date, order_store,
				order_confirm_user, order_confirm_time, order_takeout_period, order_status, order_finished_time, details);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 以下為接收App端送來新增訂單請求資訊準備(json格式)，未完成
		
//	 	BufferedReader br = request.getReader();
//		StringBuilder insertOrderJson = new StringBuilder();
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			insertOrderJson.append(line);
//		}
//		System.out.println("input: " + insertOrderJson);
//		
		Gson gson = new Gson();
//		JsonObject insertOrderJsonObj = gson.fromJson(insertOrderJson.toString(),
//		 JsonObject.class); // 轉為json物件
//		
//		String action = insertOrderJsonObj.get("action").getAsString();
//		if (action.equals("getOrdersList")) {
//			OrdersDao ordersDao = new OrdersDaoImpl();
//			List <Orders> orderList= ordersDao.getUserOrders();
//			System.out.println("查詢訂單資訊: " + orderList.toString());
//		}
		

		// 解析由Web端送來之新增訂單請求資訊
//		Integer order_user = Integer.parseInt(request.getParameter("order_user"));
//		String order_note = request.getParameter("order_note");
//		Date order_time = new Date(System.currentTimeMillis());
//		Date order_reserve_date = Date.valueOf(request.getParameter("order_reserve_date"));
//		Integer order_store = Integer.parseInt(request.getParameter("order_store"));
//		Integer order_confirm_user = Integer.parseInt(request.getParameter("order_confirm_user"));
//		Date order_confirm_time = new Date(System.currentTimeMillis());
//		String order_takeout_period = request.getParameter("order_takeout_period");
//		String order_status = request.getParameter("order_status");
//		Date order_finished_time = new Date(System.currentTimeMillis());
//		Orders insertOrderBean = new Orders(null, order_user, order_note, order_time, order_reserve_date, order_store,
//				order_confirm_user, order_confirm_time, order_takeout_period, order_status, order_finished_time, null);
//		System.out.println("insertOrderBean: " + insertOrderBean);
//		
//		Integer order_food = Integer.parseInt(request.getParameter("order_food"));
//		Integer order_quantity = Integer.parseInt(request.getParameter("order_quantity"));
//		
//		Set<Order_details> details = new HashSet<>();
//		Order_details orderDetailsBean = new Order_details(null, 0, order_food, order_quantity);
//		details.add(orderDetailsBean);
//		insertOrderBean.setDetails(details);
//		System.out.println("新增訂單資訊: " + insertOrderBean.toString());
//		String insertOrderJsonStr = gson.toJson(insertOrderBean); // Object to JSON
//		System.out.println("新增訂單資訊JSON: " + insertOrderJsonStr);
		String insertOrderInitJsonStr = gson.toJson(insertOrderInit); // Object to JSON
		
		OrdersDao ordersDao = new OrdersDaoImpl();
//		ordersDao.insertOrder(insertOrderBean);
		ordersDao.insertOrder(insertOrderInit);
//		writeText(response, insertOrderJsonStr);
		writeText(response, insertOrderInitJsonStr);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	// 測試response用，實際response待修改
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
