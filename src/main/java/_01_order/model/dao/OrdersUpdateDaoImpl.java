package _01_order.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _01_global.Db;
import _01_order.model.ORDERS;

public class OrdersUpdateDaoImpl implements OrdersUpdateDao {
	Connection conn;
	DataSource ds = null;

	public OrdersUpdateDaoImpl() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(Db.JNDI_DB_NAME);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	// 更新接單人員編號
		public int updateOrderConfirmUser(ORDERS order) {
			String updateOrderConfirmUserSql = "UPDATE ORDERS SET" 
					+" order_confirm_user = " + order.getOrder_confirm_user()
					+" ,order_confirm_time = " + order.getOrder_confirm_time()
					+" ,order_status = " + order.getOrder_status()
					+" ,order_finished_time = " + order.getOrder_finished_time()
					+" WHERE order_id = " + order.getOrder_id();
			int count = 0;
			try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(updateOrderConfirmUserSql);) {
				count = ps.executeUpdate();
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
			return count;
		}

		// 更新交易確認狀態
		public int updateOrderStatus(String orderStatus, Integer orderId) {
			String updateOrderStatus = "UPDATE ORDERS SET" 
					+ " order_status = ? " 
					+ " WHERE order_id = ? "; 
			int count = 0;
			try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(updateOrderStatus);) {
				ps.setString(1, orderStatus);
				ps.setInt(2, orderId);		
				count = ps.executeUpdate();
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
			return count;
		}
	
	

}
