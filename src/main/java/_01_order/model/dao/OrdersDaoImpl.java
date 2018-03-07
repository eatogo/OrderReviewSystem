package _01_order.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _01_global.Db;
import _01_order.model.FOODS;
import _01_order.model.ORDERS;
import _01_order.model.ORDER_DETAILS_Extra;

public class OrdersDaoImpl implements OrdersDao {
	Connection conn;
	DataSource ds = null;
	private Integer order_user = null; // 查用戶訂單使用

	// 以下為使用DriverManger連線方式準備
	// public OrdersDaoImpl() {
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// }

	public OrdersDaoImpl() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(Db.JNDI_DB_NAME);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public int insertOrder(ORDERS ob) {
		int n = 0;
		// String insertOrderSql = "INSERT INTO ORDERS ("
		// + " order_id, order_user, order_note, order_time, order_reserve_date,"
		// + " order_store, order_confirm_user, order_confirm_time,
		// order_takeout_period,"
		// + " order_status, order_finished_time)" + " values(null, ?, ?, ?, ?, ?, ?, ?,
		// ?, ?, ?)";
		String insertOrderSql = "INSERT INTO ORDERS ("
				+ " order_user, order_note, order_time, order_reserve_date, order_store,"
				+ " order_takeout_period, order_status) " + " values(?, ?, ?, ?, ?, ?, ?)";

		String insertOrderDetailSql = "INSERT INTO ORDER_DETAILS ("
				+ " order_detail_id, order_id, order_food, order_quantity)" + " values(null, ?, ?, ?);";

		ResultSet generatedKeys = null;

		try (Connection conn = ds.getConnection();
				// Connection conn = DriverManager.getConnection(Db.URLALL); //
				// DriverManager連線方式使用
				PreparedStatement ps1 = conn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);) {

			ps1.setInt(1, ob.getOrder_user());
			ps1.setString(2, ob.getOrder_note());
			ps1.setTimestamp(3, new Timestamp(ob.getOrder_time().getTime()));
			ps1.setDate(4, ob.getOrder_reserve_date());
			ps1.setInt(5, ob.getOrder_store());
			ps1.setString(6, ob.getOrder_takeout_period());
			ps1.setString(7, ob.getOrder_status());
			n = ps1.executeUpdate();
			System.out.println("成功新增一筆訂單");

			int id = 0;
			generatedKeys = ps1.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Creating Order failed, no generated key obtained.");
			}

			// Set<ORDER_DETAILS> details = ob.getDetails();
			Set<ORDER_DETAILS_Extra> detailsEx = ob.getDetailsExtra();
			try (PreparedStatement ps2 = conn.prepareStatement(insertOrderDetailSql);) {
				for (ORDER_DETAILS_Extra odb_ex : detailsEx) {
					ps2.setInt(1, id);
					ps2.setInt(2, odb_ex.getOrder_food());
					ps2.setInt(3, odb_ex.getOrder_quantity());
					ps2.executeUpdate();
					ps2.clearParameters();
				}
				System.out.println("成功新增一筆訂單明細");
				for (ORDER_DETAILS_Extra odb_ex1 : detailsEx) {
					System.out.println(odb_ex1.toString()); // Console印出寫入資訊
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return n;
	}

	@Override
	// 取得單筆訂單與明細
	public ORDERS getOrderByOrderId(int orderId) {
		ORDERS ob = null;
		ORDER_DETAILS_Extra odb_ex = null;
		// ORDER_DETAILS odb = null;
		Set<ORDER_DETAILS_Extra> odb_ex_set = null;
		// Set<ORDER_DETAILS> odb_set = null;
		String getOrdersSql = "SELECT * FROM ORDERS WHERE order_id = ?";
		// String getOrderDetailsSql = "SELECT * FROM ORDER_DETAILS WHERE order_id = ?";
		String getOrderDetailsExSql = "SELECT od.*, f.food_name, f.food_price"
				+ " FROM ORDER_DETAILS od JOIN FOODS f ON od.order_food = f.food_id " + " WHERE od.order_id = ?; ";
		// String getOrderDetailExPicSql = "SELECT od.*, f.food_name, f.food_price,
		// f.food_pic_mdpi"
		// + " FROM ORDER_DETAILS od JOIN FOODS f ON od.order_food = f.food_id " + "
		// WHERE od.order_id = ?; ";

		try (Connection conn = ds.getConnection();
				// Connection conn = DriverManager.getConnection(Db.URLALL); DriverManager連線方式使用
				PreparedStatement ps1 = conn.prepareStatement(getOrdersSql);
				PreparedStatement ps2 = conn.prepareStatement(getOrderDetailsExSql);
		// PreparedStatement ps2 = conn.prepareStatement(getOrderDetailExPicSql); //
		// 包含讀取圖片

		) {
			ps1.setInt(1, orderId);
			try (ResultSet rs1 = ps1.executeQuery();) {
				if (rs1.next()) {
					Integer order_id = rs1.getInt("order_id");
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
					ob = new ORDERS(order_id, order_user, order_note, order_time, order_reserve_date, order_store,
							order_confirm_user, order_confirm_time, order_takeout_period, order_status,
							order_finished_time, null, null);
				}
			}
			ps2.setInt(1, orderId);
			try (ResultSet rs2 = ps2.executeQuery();) {
				odb_ex_set = new HashSet<>();
				while (rs2.next()) {
					Integer order_detail_id = rs2.getInt("order_detail_id");
					Integer order_id = rs2.getInt("order_id");
					Integer order_food = rs2.getInt("order_food");
					Integer order_quantity = rs2.getInt("order_quantity");

					String food_name = rs2.getString("food_name");
					Integer food_price = rs2.getInt("food_price");

					odb_ex = new ORDER_DETAILS_Extra(order_detail_id, order_id, order_food, order_quantity, food_name,
							food_price, null);
					// odb = new ORDER_DETAILS(order_detail_id, order_id, order_food,
					// order_quantity);

					System.out.println("odb_ex:" + odb_ex.toString());
					odb_ex_set.add(odb_ex);
				}
				ob.setDetailsExtra(odb_ex_set);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ob;
	}

	// 取得使用者多筆訂單明細
	@Override
	public List<ORDERS> getOrdersDetailByUser(int userId) {
		List<ORDERS> orderDetail = new ArrayList<>();
		String getOrdersByUserSql = "SELECT order_id FROM ORDERS WHERE order_user = ? ORDER BY order_time desc;";

		try (Connection conn = ds.getConnection();
				// Connection conn = DriverManager.getConnection(Db.URLALL); DriverManager連線方式使用
				PreparedStatement ps = conn.prepareStatement(getOrdersByUserSql);) {
			ps.setInt(1, userId); // 待規劃 orderUser取得程序，及App端登入的取得方式。
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Integer orderId = rs.getInt("order_id");
					orderDetail.add(getOrderByOrderId(orderId));
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return orderDetail;
	}

	// 取得用戶訂單列表
	@Override
	public List<Integer> getOrderListByUser(int userId) {
		List<Integer> userOrders = new ArrayList<>();
		String getOrdersByUserSql = "SELECT * FROM ORDERS WHERE order_user = ?;";
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(getOrdersByUserSql);) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer order_id = rs.getInt("order_id");
				userOrders.add(order_id);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		System.out.println("用戶訂單清單" + userOrders);
		return userOrders;
	}

	// 取得店家訂單列表
	@Override
	public List<Integer> getOrderListByStore(int orderStore) {
		List<Integer> storeOrders = new ArrayList<>();
		String getOrdersByStoreSql = "SELECT * FROM ORDERS WHERE order_store = ?";
		String sql_2 = "";
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(getOrdersByStoreSql);) {
			ps.setInt(1, orderStore);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer order_store = rs.getInt("orderStore");
				storeOrders.add(order_store);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		System.out.println("店家訂單清單" + storeOrders);
		return storeOrders;
	}

	@Override
	public List<ORDERS> getOrdersDeatilByStore(int storeId) {
		List<ORDERS> orderDetail = new ArrayList<>();
		String getOrdersByStore = "SELECT order_id FROM ORDERS WHERE order_store = ? ORDER BY order_time desc;";
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(getOrdersByStore);) {
			ps.setInt(1, storeId); 
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Integer orderId = rs.getInt("order_id");
					orderDetail.add(getOrderByOrderId(orderId));
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		

		return orderDetail;
	}

	// 變更交易確認狀態
	public int updateOrderStatus_0(ORDERS order) {
		String updateOrderStatus_0 = "UPDATE ORDERS SET" + " order_confirm_user = " + order.getOrder_confirm_user()
				+ ", order_confirm_time = " + order.getOrder_confirm_time() + ", order_status = "
				+ order.getOrder_status() + ", order_finished_time = " + order.getOrder_finished_time()
				+ " WHERE order_id = " + order.getOrder_id();
		int count = 0;
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(updateOrderStatus_0);) {
			count = ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return count;
	}

	// 變更交易確認狀態
	public int updateOrderStatus_1(ORDERS order) {
		String updateOrderStatus_1 = "UPDATE ORDERS SET" + ", order_status = " + order.getOrder_status()
				+ ", order_finished_time = " + order.getOrder_finished_time() + " WHERE order_id = "
				+ order.getOrder_id();
		int count = 0;
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(updateOrderStatus_1);) {
			count = ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return count;
	}

	// 取得Blob格式餐點圖片food_pic_mdpi
	public byte[] getFoodPic(Integer food_id) {
		String sql = "SELECT food_pic_mdpi FROM FOODS WHERE food_id = ?;";
		byte[] food_pic_mdpi = null;
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, food_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				food_pic_mdpi = rs.getBytes("food_pic_mdpi");
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return food_pic_mdpi;
	}

	// 取得餐點圖片連結food_pic_mdpi
	public FOODS getFoods(Integer food_id) {
		String sql = "SELECT food_name, food_price FROM FOODS WHERE food_id = ?;";
		FOODS foods = null;
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, food_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String food_name = rs.getString("food_name");
				Integer food_price = rs.getInt("food_price");
				foods = new FOODS(food_id, food_name, food_price, null, null, null, null, null, null, null, null, null,
						null);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return foods;
	}

	@Override
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

	@Override
	public List<String> getFoodPicUrls(int orderId) {
		String sql = "SELECT food_pic_mdpi FROM FOODS WHERE food_id IN (SELECT order_food FROM ORDER_DETAILS WHERE order_id = 6);";
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
	@Override
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
