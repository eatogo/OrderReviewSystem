package _01_global;

public class USER_STATUSES {
	private String status_type;
	private String status_description;
	
	public USER_STATUSES() {
	}

	public USER_STATUSES(String status_type, String status_description) {
		super();
		this.status_type = status_type;
		this.status_description = status_description;
	}

	public String getStatus_type() {
		return status_type;
	}

	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}

	public String getStatus_description() {
		return status_description;
	}

	public void setStatus_description(String status_description) {
		this.status_description = status_description;
	}

	@Override
	public String toString() {
		return "USER_STATUSES [status_type=" + status_type + ", status_description=" + status_description + "]";
	}
	
}
