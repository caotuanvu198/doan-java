package vu.dev.tools;

public class DBConstant {
	// For Mysql connect
	public static String HOSTNAME = "localhost";
	public static String DB_NAME = "manager_room";
	public static String DB_USER = "root";
	public static String DB_PASS = "Saygoodbye123.";

	// Tables
	public static String TABLE_CUSTOMER = "customer";
	public static String CUSTOMER_ID = "id";
	public static String CUSTOMER_NAME = "name";
	public static String CUSTOMER_OLD = "old";
	public static String CUSTOMER_SEX = "sex";
	public static String CUSTOMER_ADDRESS = "address";

	// Tables
	public static String TABLE_ROOM = "room";
	public static String ROOM_ID = "id";
	public static String ROOM_NAME = "name";
	public static String ROOM_STATUS = "status";
	public static String ROOM_DESC = "desc";
	public static String ROOM_PRICE = "price";

	// Tables
	public static String TABLE_HIRE_INFOR = "hire_info";
	public static String TABLE_HIRE_ID = "id";
	public static String HIRE_CUS_NAME = "name_cus";
	public static String HIRE_ROOM_NAME = "name_room";
	public static String HIRE_DAY_FROM = "date_start";
	public static String HIRE_DAY_END = "date_end";
	public static String HIRE_STATUS = "status";
 
}
