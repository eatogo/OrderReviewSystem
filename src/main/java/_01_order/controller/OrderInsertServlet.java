package _01_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _01_order.model.ORDERS;
import _01_order.model.ORDER_DETAILS_Extra;
import _01_order.model.dao.OrdersDao;
import _01_order.model.dao.OrdersDaoImpl;

/**
 * @author Jason
 * 此Servlet目前以無使用
 *
 */

@WebServlet("/OrderInsertServlet.do")
public class OrderInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE_1 = "text/html; charset=UTF-8";
	private static final String CONTENT_TYPE_2 = "application/json; charset=UTF-8";
	private ORDERS insertOrderInit = null;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		 //測試解析由Web端送來之新增訂單請求(無ORDER_DETAILS)
		 Integer order_user = Integer.parseInt(request.getParameter("order_user"));
		 String order_note = request.getParameter("order_note");
		 Date order_time = new Date(System.currentTimeMillis());
		 Date order_reserve_date = Date.valueOf(request.getParameter("order_reserve_date"));
		 Integer order_store = Integer.parseInt(request.getParameter("order_store"));
		 Integer order_confirm_user = Integer.parseInt(request.getParameter("order_confirm_user"));
		 Date order_confirm_time = new Date(System.currentTimeMillis());
		 String order_takeout_period = request.getParameter("order_takeout_period");
		 String order_status = request.getParameter("order_status");
		 
		 Integer order_food = Integer.parseInt(request.getParameter("order_food"));
		 Integer order_quantity = Integer.parseInt(request.getParameter("order_quantity"));
		 
		 ORDERS insertOrderBean = new ORDERS(null, order_user, order_note, order_time,
				 order_reserve_date, order_store,
				 order_confirm_user, order_confirm_time, order_takeout_period, order_status,
				 null, null, null);
		
		 Set<ORDER_DETAILS_Extra> details = new HashSet<>();
		 ORDER_DETAILS_Extra orderDetailsBean = new ORDER_DETAILS_Extra(null, 0, order_food,
				 order_quantity, null, null, null);
		 
		 System.out.println("insertOrderBean: " + insertOrderBean);
		
		 
		 details.add(orderDetailsBean);
		 insertOrderBean.setDetailsExtra(details);
		 System.out.println("新增訂單資訊: " + insertOrderBean.toString());
		 System.out.println("新增訂單資訊JSON: " + new Gson().toJson(insertOrderBean));
		 Gson gson = new Gson();
		 JsonObject insertOrderJsonObj = gson.fromJson(insertOrderBean.toString(), JsonObject.class);
		 System.out.println("新增訂單資訊JSON: " + insertOrderJsonObj);


		OrdersDao ordersDao = new OrdersDaoImpl();
		ordersDao.insertOrder(insertOrderInit);

		writeText(response, insertOrderInit.toString());
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
