package _01_order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import _01_order.model.ORDER_DETAILS;
import _01_order.model.ORDER_DETAILS_Extra;
import _01_order.model.ORDERS;
import _01_order.model.dao.OrdersDao;
import _01_order.model.dao.OrdersDaoImpl;
import _01_order.model.dao.OrdersServiceImpl;

@WebServlet("/OrderInsertServletApp.do")
public class OrderInsertServletApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE_1 = "text/html; charset=UTF-8";
	private static final String CONTENT_TYPE_2 = "application/json; charset=UTF-8";
	JsonObject orderJsonObj = null;
	JsonObject orderJsonObj_init = null;
	String orderJsonString_init = null;

	public OrderInsertServletApp() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init();

		Integer order_user = 1;
		String order_note = null;
		java.util.Date order_time = new java.util.Date(System.currentTimeMillis());
		Date order_reserve_date = Date.valueOf("2018-02-02");
		Integer order_store = 1;
		// Integer order_confirm_user = null;
		// Date order_confirm_time = new Date(System.currentTimeMillis());
		String order_takeout_period = "A";
		String order_status = "ordered";
		// Date order_finished_time = new Date(System.currentTimeMillis());
		Set<ORDER_DETAILS_Extra> detailsEx = new LinkedHashSet<>();
		detailsEx.add(new ORDER_DETAILS_Extra(null, 1, 1, 1, "扒帶魚", 479, null));
		detailsEx.add(new ORDER_DETAILS_Extra(null, 1, 2, 2, "公仔麵", 480, null));
		detailsEx.add(new ORDER_DETAILS_Extra(null, 1, 3, 3, "吸管麵", 2241, null));
		
		ORDERS insertOrderInit = new ORDERS(null, order_user, order_note, order_time, order_reserve_date, order_store, null,
				null, order_takeout_period, order_status, null, null, detailsEx);
		
		System.out.println("insertOrderInit為: " + insertOrderInit.toString());
		
		Gson gson = new Gson();
//		Gson gson = new GsonBuilder().create();
		
		
//		orderJsonObj_init = gson.fromJson(gson.toJson(insertOrderInit).toString(), JsonObject.class);
		orderJsonObj_init = new Gson().fromJson(gson.toJson(insertOrderInit).toString(), JsonObject.class);
		orderJsonObj_init.addProperty("action", "insertOrder");
		orderJsonObj_init.addProperty("order", gson.toJson(orderJsonObj_init));
		System.out.println("orderJsonObj:為 " + orderJsonObj_init);
		
		orderJsonObj = gson.fromJson(gson.toJson(orderJsonObj_init), JsonObject.class);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 以下為接收App端送來之新增訂單請求準備(json格式)

		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder orderJson = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			orderJson.append(line);
		}
		System.out.println("若有來自BufferredReader資訊先列印出" + orderJson);

		OrdersDao ordersDao = new OrdersDaoImpl();
		String action = orderJsonObj.get("action").getAsString();
		
		if (action.equals("getOrderByUser")) {
			Integer userId = orderJsonObj.get("user_id").getAsInt();
			List<ORDERS> ordersDetail = ordersDao.getOrdersDetailByUser(userId);
			writeText(response, gson.toJson(ordersDetail));
			System.out.println("查詢訂單資訊: " + ordersDetail.toString());
		} else if (action.equals("getFoodPicUrls")) {
			Integer orderId = orderJsonObj.get("order_id").getAsInt();
			List<String> foodPicUrls = ordersDao.getFoodPicUrls(orderId);
			writeText(response, gson.toJson(foodPicUrls));
			System.out.println("查詢訂單圖片網址: " + foodPicUrls.toString());
		} else if (action.equals("getFoodPicByte")) {
			OutputStream os = response.getOutputStream();
			Integer foodId = orderJsonObj.get("food_id").getAsInt();
			byte[] food_pic_mdpi = ordersDao.getFoodPicMdpiByte(foodId);
			if (food_pic_mdpi != null) {
				response.setContentType("image/jpeg");
				response.setContentLength(food_pic_mdpi.length);
				System.out.println("成功讀取圖片");
			}
			os.write(food_pic_mdpi);
		} else if (action.equals("insertOrder")) {
			ORDERS insertOrder = gson.fromJson(orderJsonObj.get("order").getAsString(), ORDERS.class);
			int count = 0;
			count = ordersDao.insertOrder(insertOrder);
			OrdersServiceImpl orderService = new OrdersServiceImpl();
			orderService.processOrder(insertOrder);
			orderService.findOrderAmount(insertOrder);

			writeText(response, insertOrder.toString());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	// 測試response用，實際response待修改
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE_1);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
