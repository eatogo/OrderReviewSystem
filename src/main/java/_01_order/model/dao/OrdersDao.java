package _01_order.model.dao;

import java.util.List;

import _01_order.model.ORDERS;

public interface OrdersDao {

	int insertOrder(ORDERS ob);

	ORDERS getOrderByOrderId(int orderId);

	List<ORDERS> getOrderByUser(int orderUser);
	
	List<Integer> getOrderList(int userId);
	
	String getFoodPicUrl(int foodId);
	
	List<String> getFoodPicUrls(int orderId);
	
	byte[] getFoodPicMdpiByte(int foodId); //若資料庫儲存圖片時改用
	
	

}
