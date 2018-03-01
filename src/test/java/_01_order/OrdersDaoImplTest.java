package _01_order;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.junit.Before;

import _01_global.Db;

public class OrdersDaoImplTest {

	Connection conn;
	DataSource ds = null;


	public OrdersDaoImplTest() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(Db.JNDI_DB_NAME);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	List<String> getFoodPicMdpiList(int orderId){
		String sql = "SELECT food_pic_mdpi FROM FOODS WHERE food_id IN (SELECT order_food FROM ORDER_DETAILS WHERE order_id = 6);";
		List<String> foodPicList = new ArrayList<String>();
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String food_pic_mdpi = rs.getString(1);
				foodPicList.add(food_pic_mdpi);
			}
			System.out.println(foodPicList.toString());
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return foodPicList;
	}
}
	

//	@Test
//	public void testOrderDaoImpl() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testInsertOrder() {
//		fail("Not yet implemented");
//	}


