package _01_order.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _01_global.Db;
import _01_order.model.ORDERS;
import _01_order.model.ORDER_DETAILS_Extra;

public class OrdersServiceImpl {

	Connection conn;
	DataSource ds = null;

	public OrdersServiceImpl() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(Db.JNDI_DB_NAME);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void processOrder(ORDERS ob) {
		Set<ORDER_DETAILS_Extra> detailsEx = ob.getDetailsExtra();
		Integer order_id = ob.getOrder_id();
		for (ORDER_DETAILS_Extra odb : detailsEx) {
			processOrderDetail(odb, order_id);
		}
	}

	public void processOrderDetail(ORDER_DETAILS_Extra odb, Integer order_id) {
		Integer order_food = odb.getOrder_food();
		Integer order_quantity = odb.getOrder_quantity();
		updateFoodLimit(order_food, order_quantity);
	}

	public int updateFoodLimit(int food_id, int order_quantity) {
		int n = 0;
		int food_stock = 0;
		String getfoodLimitsql = "SELECT food_limit FROM FOODS WHERE food_id = ?;";
		String updatefoodLimit = "UPDATE FOODS SET food_limit = food_limit - ? WHERE food_id = ?;";

		try (Connection conn = ds.getConnection(); PreparedStatement ps1 = conn.prepareStatement(getfoodLimitsql);) {
			ps1.setInt(1, food_id);
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {
				food_stock = rs.getInt(1);
				if (food_stock < order_quantity) {
					String message = "庫存數量不足";
					throw new RuntimeException(message);
				} else {
					;
				}
				try (PreparedStatement ps2 = conn.prepareStatement(updatefoodLimit);) {
					ps2.setInt(1, order_quantity);
					ps2.setInt(2, food_id);
					n = ps2.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return n;
	}

	public double findOrderAmount(ORDERS ob) {
		// Integer orderId = ob.getOrder_id();
		// OrdersDao orderDao = new OrdersDaoImpl();
		// ORDERS obEx = orderDao.getOrderById(orderId);
		double total = 0;
		// double price[] = 0;
		// Set<ORDER_DETAILS_Extra> odEx = obEx.getDetailsExtra();
		Set<ORDER_DETAILS_Extra> odExArr = ob.getDetailsExtra();
		for (ORDER_DETAILS_Extra odEx : odExArr) {
			double subtotal = odEx.getOrder_quantity() * odEx.getFood_price();
			total += subtotal;
		}
		System.out.println("總金額: " + total);
		return total;
	}

	
	private Map<Integer, ORDER_DETAILS_Extra> cart = new LinkedHashMap<>();

	public double getSubtotal() {
		double subTotal = 0;
		Set<Integer> set = cart.keySet();
		for (int n : set) {
			String food_name = cart.get(n).getFood_name();
			double food_price = cart.get(n).getFood_price();
			Integer order_quantity = cart.get(n).getOrder_quantity();
			subTotal += food_price * order_quantity;
		}
		return subTotal;
	}

	//未完成
	public void listDetails() {
		Set<Integer> set = cart.keySet();
		for (Integer k : set) {
			System.out.printf("food_name=, order_quamtity=, food_price=\n", k, cart.get(k).getFood_name(),
					cart.get(k).getOrder_quantity(), cart.get(k).getFood_price());
		}
	}
	
	

}
