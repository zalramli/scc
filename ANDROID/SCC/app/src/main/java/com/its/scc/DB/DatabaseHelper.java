package com.its.scc.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.its.scc.DB.CartSoftware.DBCartSoftware;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(@Nullable Context context) {
		super(context, DBCartSoftware.DB_NAME, null, DBCartSoftware.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(DBCartSoftware.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + DBCartSoftware.TABLE_NAME);
		onCreate(db);
	}

	// untuk insert info
	public long insertInfo(String id_software, String nama) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(DBCartSoftware.C_ID_SOFTWARE, id_software);
		values.put(DBCartSoftware.C_NAMA, nama);

		long id = db.insert(DBCartSoftware.TABLE_NAME, null, values);

		db.close();
		return id;
	}
}
