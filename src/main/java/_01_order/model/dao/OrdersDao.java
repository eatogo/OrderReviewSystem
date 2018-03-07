package _01_order.model.dao;

import java.util.List;

import _01_order.model.ORDERS;

public interface OrdersDao {

	int insertOrder(ORDERS ob);

	ORDERS getOrderByOrderId(int orderId);

	List<ORDERS> getOrdersDetailByUser(int orderUser);
	
	List<ORDERS> getOrdersDeatilByStore(int storeId);
	
	List<Integer> getOrderListByUser(int userId);
	
	
	List<Integer> getOrderListByStore (int orderStore);
	
	String getFoodPicUrl(int foodId);
	
	List<String> getFoodPicUrls(int orderId);
	
	byte[] getFoodPicMdpiByte(int foodId); //若資料庫儲存圖片時改用
	
	

}
