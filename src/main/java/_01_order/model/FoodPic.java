package _01_order.model;

public class FoodPic {
	private Integer food_id;
	private String food_pic_hdpi;
	private String food_pic_ldpi;
	private String food_pic_mdpi;
	private String food_pic;

	public FoodPic() {
		super();
	}

	public FoodPic(Integer food_id, String food_pic_hdpi, String food_pic_ldpi, String food_pic_mdpi, String food_pic) {
		super();
		this.food_id = food_id;
		this.food_pic_hdpi = food_pic_hdpi;
		this.food_pic_ldpi = food_pic_ldpi;
		this.food_pic_mdpi = food_pic_mdpi;
		this.food_pic = food_pic;
	}

	public Integer getFood_id() {
		return food_id;
	}

	public void setFood_id(Integer food_id) {
		this.food_id = food_id;
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

}