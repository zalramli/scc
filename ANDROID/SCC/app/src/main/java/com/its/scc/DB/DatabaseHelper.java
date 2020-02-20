package com.its.scc.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.its.scc.Models.TableSoftware;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(@Nullable Context context) {
		super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(DBConstants.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_NAME);
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

	public ArrayList<TableSoftware> getAllData(String orderBy) {

		ArrayList<TableSoftware> arrayList = new ArrayList<>();

		// query for select all
		String selectQuery = "SELECT * FROM " + DBConstants.TABLE_NAME + " ORDER BY " + orderBy;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToNext()) {

			do {

				TableSoftware tableSoftware = new TableSoftware(
					"" + cursor.getInt(cursor.getColumnIndex(DBConstants.C_ID)),
					"" + cursor.getString(cursor.getColumnIndex(DBConstants.C_ID_SOFTWARE)),
					"" + cursor.getString(cursor.getColumnIndex(DBConstants.C_NAMA))
				);

				arrayList.add(tableSoftware);

			} while (cursor.moveToNext());

		}

		db.close();
		return arrayList;

	}
}
