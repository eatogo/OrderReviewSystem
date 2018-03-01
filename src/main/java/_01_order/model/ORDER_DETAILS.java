package _01_order.model;

public class ORDER_DETAILS {
	private Integer order_detail_id;
	private Integer order_id;
	private Integer order_food;
	private Integer order_quantity;
	
		public ORDER_DETAILS() {
		super();
	}

	public ORDER_DETAILS(Integer order_detail_id, Integer order_id, Integer order_food, Integer order_quantity) {
		super();
		this.order_detail_id = order_detail_id;
		this.order_id = order_id;
		this.order_food = order_food;
		this.order_quantity = order_quantity;
	}


	public Integer getOrder_detail_id() {
		return order_detail_id;
	}


	public void setOrder_detail_id(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
	}


	public Integer getOrder_id() {
		return order_id;
	}


	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}


	public Integer getOrder_food() {
		return order_food;
	}


	public void setOrder_food(Integer order_food) {
		this.order_food = order_food;
	}


	public Integer getOrder_quantity() {
		return order_quantity;
	}


	public void setOrder_quantity(Integer order_quantity) {
		this.order_quantity = order_quantity;
	}

	@Override
	public String toString() {
		return "OrderDetailBean [order_detail_id=" + order_detail_id + ", order_id=" + order_id + ", order_food="
				+ order_food + ", order_quantity=" + order_quantity + "]";
	}

}
