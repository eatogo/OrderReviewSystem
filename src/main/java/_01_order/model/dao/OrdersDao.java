package _01_order.model.dao;

import java.util.List;

import _01_order.model.Orders;

public interface OrdersDao {
	
	int insertOrder(Orders ob);
	
	Orders getOrderById (int order_id);
	
	List<Integer> getOrderList(int user_id);
	
	List<Orders> getUserOrders (int user_id);
	

}
