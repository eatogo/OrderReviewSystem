package _01_order.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _01_global.Db;
/**
 * 此DAO目前完全未使用，主要為餐點圖片以URL方式儲存準備。
 * @author NTUT
 *
 */

public class OrdersImageDaoImpl {

	Connection conn;
	DataSource ds = null;

	public OrdersImageDaoImpl() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(Db.JNDI_DB_NAME);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	// 取得餐點圖片URL，目前專案中未使用
	public String getFoodPicUrl(int foodId) {
		String sql = "SELECT food_pic_mdpi FROM FOODS WHERE food_id = ?;";
		String foodPicUrl = null;
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, foodId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				foodPicUrl = rs.getString(1);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return foodPicUrl;
	}

	// 取得餐點圖片URL，目前專案中未使用
	public List<String> getFoodPicUrls(int orderId) {
		String sql = "SELECT food_pic_mdpi FROM FOODS WHERE food_id IN (SELECT order_food FROM ORDER_DETAILS WHERE order_id = ?);";
		List<String> foodPicUrls = new ArrayList<String>();
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String food_pic_mdpi = rs.getString(1);
				foodPicUrls.add(food_pic_mdpi);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return foodPicUrls;
	}

	// 若資料庫儲存圖片時使用
	public byte[] getFoodPicMdpiByte(int foodId) {
		String sql = "SELECT food_pic_mdpi FROM FOODS WHERE food_id = ?;";
		byte[] foodPicMdpiByte = null;
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, foodId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				foodPicMdpiByte = rs.getBytes(1);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return foodPicMdpiByte;
	}

}
