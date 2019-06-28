package vu.dev.data.model;

public class HireInformation extends BaseModel {

	private int id;
	private Customer customer;
	private Room room;
	private String cus_name;
	private String room_name;
	private String dateFrom;
	private String dateEnd;
	private String status;
	private int price_room;

	public HireInformation(int id, Customer customer, Room room, String dateFrom, String dateEnd) {
		super();
		this.id = id;
		this.customer = customer;
		this.room = room;
		this.dateFrom = dateFrom;
		this.dateEnd = dateEnd;
	}

	public HireInformation(int id, String cus_name, String room_name, String dateFrom, String dateEnd, String status,
			int price) {
		super();
		this.id = id;
		this.cus_name = cus_name;
		this.room_name = room_name;
		this.dateFrom = dateFrom;
		this.dateEnd = dateEnd;
		this.status = status;
		this.price_room = price;
	}

	public int getPriceRoom() {
		return price_room;
	}

	public void setPriceRoom(int price_room) {
		this.price_room = price_room;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCusName() {
		return cus_name;
	}

	public void setCusName(String cus_name) {
		this.cus_name = cus_name;
	}

	public String getRoomName() {
		return room_name;
	}

	public void setRoomName(String room_name) {
		this.room_name = room_name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getDateEnd() {
		return dateEnd;
	}

}
