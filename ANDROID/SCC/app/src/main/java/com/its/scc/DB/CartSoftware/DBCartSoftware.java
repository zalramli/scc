package com.its.scc.DB.CartSoftware;

public class DBCartSoftware {

	// db name
	public static final String DB_NAME = "SCC";

	// db version
	public static final int DB_VERSION = 1;

	// db table
	public static final String TABLE_NAME = "CART_SOFTWARE";

	// table columns
	public static final String C_ID = "ID";
	public static final String C_ID_SOFTWARE = "ID_SOFTWARE";
	public static final String C_NAMA = "NAMA";

	// query
	public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
		+ C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ C_ID_SOFTWARE + " TEXT,"
		+ C_NAMA + " TEXT"
		+ ");";
}
