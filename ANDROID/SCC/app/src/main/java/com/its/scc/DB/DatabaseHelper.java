package com.its.scc.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.its.scc.Models.Software;
import com.its.scc.Models.User;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(@Nullable Context context) {
		super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(DBConstants.CREATE_TABLE);

		db.execSQL(DBConstants.CREATE_TABLE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_NAME);

		db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_NAME_USER);
		onCreate(db);
	}

	// untuk insert info
	public long insertInfo(String id_software, String nama) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(DBConstants.C_ID_SOFTWARE, id_software);
		values.put(DBConstants.C_NAMA, nama);

		long id = db.insert(DBConstants.TABLE_NAME, null, values);

		db.close();
		return id;
	}

	public ArrayList<Software> getAllData(String orderBy) {

		ArrayList<Software> dataModelArrayList = new ArrayList<>();

		// query for select all
		String selectQuery = "SELECT * FROM " + DBConstants.TABLE_NAME + " ORDER BY " + orderBy;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToNext()) {

			do {

				Software playerModel = new Software();

				int id = cursor.getInt(cursor.getColumnIndex(DBConstants.C_ID));
				String id_software = cursor.getString(cursor.getColumnIndex(DBConstants.C_ID_SOFTWARE));
				String nama = cursor.getString(cursor.getColumnIndex(DBConstants.C_NAMA));

				playerModel.setId(id);
				playerModel.setId_software(id_software);
				playerModel.setNama(nama);

				dataModelArrayList.add(playerModel);

			} while (cursor.moveToNext());

		}

		db.close();
		return dataModelArrayList;
	}

	public void deleteInfo(String id) {

		SQLiteDatabase db = getWritableDatabase();
		db.delete(DBConstants.TABLE_NAME, DBConstants.C_ID + " = ? ", new String[]{id});
		db.close();
	}

	public void deleteAll() {

		SQLiteDatabase db = getWritableDatabase();
		db.delete(DBConstants.TABLE_NAME, null, null);
		db.close();
	}

	// Start untuk user
	// untuk insert info
	public long insertInfoUser(String username, String password) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(DBConstants.C_USER_USERNAME, username);
		values.put(DBConstants.C_USER_PASSWORD, password);

		long id = db.insert(DBConstants.TABLE_NAME_USER, null, values);

		db.close();
		return id;
	}

	public ArrayList<User> getAllDataUser(String orderBy) {

		ArrayList<User> dataModelArrayList = new ArrayList<>();

		// query for select all
		String selectQuery = "SELECT * FROM " + DBConstants.TABLE_NAME_USER + " ORDER BY " + orderBy;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToNext()) {

			do {

				User playerModel = new User();

				int id = cursor.getInt(cursor.getColumnIndex(DBConstants.C_USER_ID));
				String username = cursor.getString(cursor.getColumnIndex(DBConstants.C_USER_USERNAME));
				String password = cursor.getString(cursor.getColumnIndex(DBConstants.C_USER_PASSWORD));

				playerModel.setId(id);
				playerModel.setUsername(username);
				playerModel.setPassword(password);

				dataModelArrayList.add(playerModel);

			} while (cursor.moveToNext());

		}

		db.close();
		return dataModelArrayList;
	}

	public void deleteAllUser() {

		SQLiteDatabase db = getWritableDatabase();
		db.delete(DBConstants.TABLE_NAME_USER, null, null);
		db.close();
	}
}
