package binh.dev.data.model;

public class Room extends BaseModel {
	private String name;
	private String desc;
	private double price;
	private int status;
	
	public Room(String name, String desc, double price, int status) {
		super();
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.status = status;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
