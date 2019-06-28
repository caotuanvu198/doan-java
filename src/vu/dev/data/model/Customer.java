package vu.dev.data.model;

public class Customer extends BaseModel {
	int id;
	String name;
	String sex;
	int old;

	final int MALE = 1;
	final int FEMALE = 2;

	public Customer(int id, String name, String sex, int old) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.old = old;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getOld() {
		return old;
	}

	public void setOld(int old) {
		this.old = old;
	}

}
