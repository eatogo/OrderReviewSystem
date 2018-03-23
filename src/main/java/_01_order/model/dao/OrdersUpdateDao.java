package _01_order.model.dao;

import _01_order.model.ORDERS;

public interface OrdersUpdateDao {
	
	int updateOrderConfirmUser(ORDERS order);
	
	int updateOrderStatus(String orderStatus, Integer orderId);

}
