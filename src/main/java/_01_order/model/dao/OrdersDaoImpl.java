package _01_order.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import _00_utility.Db;
import _01_order.model.Order_details;
import _01_order.model.Orders;

public class OrdersDaoImpl implements OrdersDao {
	Connection conn;

	public OrdersDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertOrder(Orders ob) {
		int n = 0;
		String insertOrderSql = "INSERT INTO ORDERS ("
				+ " order_id, order_user, order_note, order_time, order_reserve_date,"
				+ " order_store, order_confirm_user, order_confirm_time, order_takeout_period,"
				+ " order_status, order_finished_time)" + " values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String insertOrderDetailSql = "INSERT INTO ORDER_DETAILS ("
				+ " order_detail_id, order_id, order_food, order_quantity)" + " values(null, ?, ?, ?)";

		ResultSet generatedKeys = null;

		try (Connection conn = DriverManager.getConnection(Db.URLALL);
				PreparedStatement ps = conn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);) {

			ps.setInt(1, ob.getOrder_user());
			ps.setString(2, ob.getOrder_note());
			ps.setTimestamp(3, new Timestamp(ob.getOrder_time().getTime()));
			ps.setDate(4, ob.getOrder_reserve_date());
			ps.setInt(5, ob.getOrder_store());
			ps.setInt(6, ob.getOrder_confirm_user());
			ps.setTimestamp(7, new Timestamp(ob.getOrder_confirm_time().getTime()));
			ps.setString(8, ob.getOrder_takeout_period());
			ps.setString(9, ob.getOrder_status());
			ps.setTimestamp(10, new Timestamp(ob.getOrder_finished_time().getTime()));
			n = ps.executeUpdate();
			System.out.println("成功新增一筆訂單");

			int id = 0;
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Creating Order failed, no generated key obtained.");
			}

			Set<Order_details> details = ob.getDetails();
			try (PreparedStatement ps2 = conn.prepareStatement(insertOrderDetailSql);) {
				for (Order_details odb : details) {
					ps2.setInt(1, id);
					ps2.setInt(2, odb.getOrder_food());
					ps2.setInt(3, odb.getOrder_quantity());
					ps2.executeUpdate();
					System.out.println("成功新增一筆訂單明細");
					ps2.clearParameters();
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return n;
	}

	@Override
	public Orders getOrderById(int order_id) {
		Orders ob = null;
		Order_details odb = null;
		Set<Order_details> set = null;
		String getOrdersSql = "SELECT * FROM orders WHERE order_id = ?";
		String getOrderDetailsSql = "SELECT * FROM order_details WHERE order_id = ?";

		try (Connection conn = DriverManager.getConnection(Db.URLALL);
				PreparedStatement ps1 = conn.prepareStatement(getOrdersSql);
				PreparedStatement ps2 = conn.prepareStatement(getOrderDetailsSql);) {
			ps1.setInt(1, order_id);
			try (ResultSet rs1 = ps1.executeQuery();) {
				if (rs1.next()) {
					Integer orderId = rs1.getInt("order_id");
					Integer order_user = rs1.getInt("order_user");
					String order_note = rs1.getString("order_note");
					Timestamp order_time = rs1.getTimestamp("order_time");
					Date order_reserve_date = rs1.getDate("order_reserve_date");
					Integer order_store = rs1.getInt("order_store");
					Integer order_confirm_user = rs1.getInt("order_confirm_user");
					Timestamp order_confirm_time = rs1.getTimestamp("order_confirm_time");
					String order_takeout_period = rs1.getString("order_takeout_period");
					String order_status = rs1.getString("order_status");
					Timestamp order_finished_time = rs1.getTimestamp("order_finished_time");
					ob = new Orders(orderId, order_user, order_note, order_time, order_reserve_date, order_store,
							order_confirm_user, order_confirm_time, order_takeout_period, order_status,
							order_finished_time, null);
				}
			}
			ps1.setInt(1, order_id);
			try (ResultSet rs2 = ps2.executeQuery();) {
				set = new HashSet<>();
				while (rs2.next()) {
					Integer order_detail_id = rs2.getInt("order_detail_id");
					Integer orderId = rs2.getInt("order_id");
					Integer order_food = rs2.getInt("order_food");
					Integer order_quantity = rs2.getInt("order_quantity");
					odb = new Order_details(order_detail_id, orderId, order_food, order_quantity);
					set.add(odb);
				}
				ob.setDetails(set);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ob;
	}
}