package _01_order.model;


public class ORDER_DETAILS_Extra {
	private Integer order_detail_id;
	private Integer order_id;
	private Integer order_food;
	private Integer order_quantity;

	private String food_name;
	private Integer food_price;
	private String food_pic_mdpi;
	
	// String food_pic_ldpi;
	// String food_pic_hdpi;
	// String food_pic_mdpi;
	// Blob food_pic_ldpi;
	// Blob food_pic_hdpi;
	// Blob food_pic_mdpi;
	
	public ORDER_DETAILS_Extra() {
		super();
	}

	public ORDER_DETAILS_Extra(Integer order_detail_id, Integer order_id, Integer order_food, Integer order_quantity,
			String food_name, Integer food_price, String food_pic_mdpi) {
		super();
		this.order_detail_id = order_detail_id;
		this.order_id = order_id;
		this.order_food = order_food;
		this.order_quantity = order_quantity;
		this.food_name = food_name;
		this.food_price = food_price;
		this.food_pic_mdpi = food_pic_mdpi;
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

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public Integer getFood_price() {
		return food_price;
	}

	public void setFood_price(Integer food_price) {
		this.food_price = food_price;
	}

	public String getFood_pic_mdpi() {
		return food_pic_mdpi;
	}

	public void setFood_pic_mdpi(String food_pic_mdpi) {
		this.food_pic_mdpi = food_pic_mdpi;
	}

	@Override
	public String toString() {
		return "ORDER_DETAILS_Extra [order_detail_id=" + order_detail_id + ", order_id=" + order_id + ", order_food="
				+ order_food + ", order_quantity=" + order_quantity + ", food_name=" + food_name + ", food_price="
				+ food_price + ", food_pic_mdpi=" + food_pic_mdpi + "]";
	}
	
}
