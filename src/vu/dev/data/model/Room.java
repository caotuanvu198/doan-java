package vu.dev.data.model;

public class Room extends BaseModel {
	int id;
	String name;
	String desc;
	int status;
	int price;

	public final static int ACTIVE = 1;
	public final static int INACTIVE = 0;

	public Room(int id, String name, String desc, int status, int price) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.status = status;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
