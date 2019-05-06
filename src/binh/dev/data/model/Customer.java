package binh.dev.data.model;

public class Customer extends BaseModel {
	private int id;
	private String name;
	private int old;
	private String sex;
	
	public Customer(String name, int old, String sex) {
		super();
		this.name = name;
		this.old = old;
		this.sex = sex;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOld() {
		return old;
	}

	public void setOld(int old) {
		this.old = old;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
