package _01_order.model;

public class FOODS {
	private Integer food_id;
	private String food_name;
	private Integer food_price;
	private String food_intro;
	private String food_pic_hdpi;
	private String food_pic_ldpi;
	private String food_pic_mdpi;
	private String food_pic;
	private Integer food_limit;
	private String food_type;
	private Integer food_store;
	private String food_status;
	private Long food_review_count;
	
	public FOODS() {
	}

	public FOODS(Integer food_id, String food_name, Integer food_price, String food_intro, String food_pic_hdpi,
			String food_pic_ldpi, String food_pic_mdpi, String food_pic, Integer food_limit, String food_type,
			Integer food_store, String food_status, Long food_review_count) {
		super();
		this.food_id = food_id;
		this.food_name = food_name;
		this.food_price = food_price;
		this.food_intro = food_intro;
		this.food_pic_hdpi = food_pic_hdpi;
		this.food_pic_ldpi = food_pic_ldpi;
		this.food_pic_mdpi = food_pic_mdpi;
		this.food_pic = food_pic;
		this.food_limit = food_limit;
		this.food_type = food_type;
		this.food_store = food_store;
		this.food_status = food_status;
		this.food_review_count = food_review_count;
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

	public String getFood_pic_hdpi() {
		return food_pic_hdpi;
	}

	public void setFood_pic_hdpi(String food_pic_hdpi) {
		this.food_pic_hdpi = food_pic_hdpi;
	}

	public String getFood_pic_ldpi() {
		return food_pic_ldpi;
	}

	public void setFood_pic_ldpi(String food_pic_ldpi) {
		this.food_pic_ldpi = food_pic_ldpi;
	}

	public String getFood_pic_mdpi() {
		return food_pic_mdpi;
	}

	public void setFood_pic_mdpi(String food_pic_mdpi) {
		this.food_pic_mdpi = food_pic_mdpi;
	}

	public String getFood_pic() {
		return food_pic;
	}

	public void setFood_pic(String food_pic) {
		this.food_pic = food_pic;
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

	@Override
	public String toString() {
		return "FOODS [food_id=" + food_id + ", food_name=" + food_name + ", food_price=" + food_price + ", food_intro="
				+ food_intro + ", food_pic_hdpi=" + food_pic_hdpi + ", food_pic_ldpi=" + food_pic_ldpi
				+ ", food_pic_mdpi=" + food_pic_mdpi + ", food_pic=" + food_pic + ", food_limit=" + food_limit
				+ ", food_type=" + food_type + ", food_store=" + food_store + ", food_status=" + food_status
				+ ", food_review_count=" + food_review_count + "]";
	}
	
}
