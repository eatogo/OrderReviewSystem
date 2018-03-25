package _01_order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
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
import _01_order.model.dao.OrdersImageDaoImpl;
import _01_order.model.dao.OrdersServiceImpl;

/**
 * Servlet implementation class OrderInsertByUser
 */
@WebServlet("/OrderInsertByUser")
public class OrderInsertByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE_1 = "text/html; charset=UTF-8";
	private static final String CONTENT_TYPE_2 = "application/json; charset=UTF-8";
	JsonObject orderJsonObj = null;
	JsonObject orderJsonObj_init = null;
	String orderJsonString_init = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		if (action.equals("orderInsert")) {
			ORDERS insertOrder = gson.fromJson(orderJsonObj.get("order").getAsString(), ORDERS.class); // App似乎缺user_id
			int count = 0;
			count = ordersDao.insertOrder(insertOrder);
			OrdersServiceImpl orderService = new OrdersServiceImpl();
			orderService.processOrder(insertOrder);
			orderService.findOrderAmount(insertOrder);

			writeText(response, insertOrder.toString());
		}else {
			//
		}
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE_1);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}

}
