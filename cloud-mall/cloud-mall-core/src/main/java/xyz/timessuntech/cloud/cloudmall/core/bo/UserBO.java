package xyz.timessuntech.cloud.cloudmall.core.bo;

public class UserBO {

	private String id;
	private long number;
	private String name;
	private boolean enable;

	public UserBO() {
		super();
	}

	public UserBO(String name) {
		super();
		this.name = name;
	}

	public UserBO(String id, long number, String name, boolean enable) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.enable = enable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
