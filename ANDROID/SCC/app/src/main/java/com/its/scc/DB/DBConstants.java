package com.its.scc.DB;

public class DBConstants {

	// db name
	public static final String DB_NAME = "SCC";

	// db version
	public static final int DB_VERSION = 1;

	// START untuk Tabel CART SOFTWARE
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
	// END untuk Tabel CART SOFTWARE

	// START untuk Tabel USER
	public static final String TABLE_NAME_USER = "USER";

	public static final String C_USER_ID = "USER_ID";
	public static final String C_USER_USERNAME = "USER_USERNAME";

	public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_NAME_USER + " ("
		+ C_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ C_USER_USERNAME + " TEXT"
		+ ");";
	// END untuk Tabel USER
}
