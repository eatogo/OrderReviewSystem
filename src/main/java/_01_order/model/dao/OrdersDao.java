package _01_order.model.dao;

import java.util.List;

import _01_order.model.Orders;

public interface OrdersDao {
	
	int insertOrder(Orders ob);
	
	Orders getOrderById (int order_id);
	
	List<Orders> getUserOrders ();
	
	

}
