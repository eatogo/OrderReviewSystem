package _01_order.model;

public class FoodWithLatLng {
	private Integer food_id;
	private String food_name;
	private Integer food_price;
	private String food_intro;
	private Integer food_limit;
	private String food_type;
	private Integer food_store;
	private String food_status;
	private Long food_review_count;
	private Double store_latitude;
	private Double store_longitude;

	public FoodWithLatLng() {
		super();
	}

	public FoodWithLatLng(Integer food_id, String food_name, Integer food_price, String food_intro, Integer food_limit,
			String food_type, Integer food_store, String food_status, Long food_review_count, Double store_latitude,
			Double store_longitude) {
		super();
		this.food_id = food_id;
		this.food_name = food_name;
		this.food_price = food_price;
		this.food_intro = food_intro;
		this.food_limit = food_limit;
		this.food_type = food_type;
		this.food_store = food_store;
		this.food_status = food_status;
		this.food_review_count = food_review_count;
		this.store_latitude = store_latitude;
		this.store_longitude = store_longitude;
	}

	public Integer getFood_id() {
		return food_id;
	}

	public void setFood_id(Integer food_id) {
		this.food_id = food_id;
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

	public String getFood_intro() {
		return food_intro;
	}

	public void setFood_intro(String food_intro) {
		this.food_intro = food_intro;
	}

	public Integer getFood_limit() {
		return food_limit;
	}

	public void setFood_limit(Integer food_limit) {
		this.food_limit = food_limit;
	}

	public String getFood_type() {
		return food_type;
	}

	public void setFood_type(String food_type) {
		this.food_type = food_type;
	}

	public Integer getFood_store() {
		return food_store;
	}

	public void setFood_store(Integer food_store) {
		this.food_store = food_store;
	}

	public String getFood_status() {
		return food_status;
	}

	public void setFood_status(String food_status) {
		this.food_status = food_status;
	}

	public Long getFood_review_count() {
		return food_review_count;
	}

	public void setFood_review_count(Long food_review_count) {
		this.food_review_count = food_review_count;
	}

	public Double getStore_latitude() {
		return store_latitude;
	}

	public void setStore_latitude(Double store_latitude) {
		this.store_latitude = store_latitude;
	}

	public Double getStore_longitude() {
		return store_longitude;
	}

	public void setStore_longitude(Double store_longitude) {
		this.store_longitude = store_longitude;
	}

}