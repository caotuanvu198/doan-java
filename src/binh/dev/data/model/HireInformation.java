package binh.dev.data.model;

import java.sql.Timestamp;

public class HireInformation extends BaseModel {
	private Customer customer;
	private Room room;
	private Timestamp dateFrom;
	private Timestamp dateEnd;
	
	public HireInformation(Customer customer, Room room, Timestamp dateFrom, Timestamp dateEnd) {
		super();
		this.customer = customer;
		this.room = room;
		this.dateFrom = dateFrom;
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
	public Timestamp getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Timestamp getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Timestamp dateEnd) {
		this.dateEnd = dateEnd;
	}
	
}
