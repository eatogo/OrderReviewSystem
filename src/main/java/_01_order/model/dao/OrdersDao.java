package _01_order.model.dao;

import java.util.List;

import _01_order.model.ORDERS;

public interface OrdersDao {

	int insertOrder(ORDERS ob);

	ORDERS getOrderByOrderId(int orderId);

	List<ORDERS> getOrdersDetailByUser(int orderUser);
	
	List<ORDERS> getOrdersDetailByStore(int storeId);
	
	List<ORDERS> getOrdersDetailByStoreWithStatus(int storeId, String status);
	
	List<Integer> getOrderListByUser(int userId);
	
	List<Integer> getOrderListByStore (int orderStore);
	
	byte[] getFoodPic(Integer food_id);

}
