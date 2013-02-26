package com.data;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseUserHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "UserManager";
	private static final String TABLE_USER = "users";
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_NICKNAME = "nickname";
	private static final String KEY_TOTALCACHES = "totalcaches";
	private static final String KEY_TOTALPOINTS = "totalpoints";
	private static final String KEY_TEAMCOLOR = "TEAMCOLOR";
	private static final String KEY_PASSWORD = "password";
	private Context cx;

	public DatabaseUserHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.cx = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_NICKNAME + " TEXT," + KEY_TOTALCACHES + " TEXT,"
				+ KEY_TOTALPOINTS + " TEXT," + KEY_TEAMCOLOR + " TEXT,"
				+ KEY_PASSWORD + " TEXT" + ")";
		db.execSQL(CREATE_USER_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

		// Create tables again
		onCreate(db);
	}

	public void addUserInfo(User user) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, user.getUsername()); // Contact Name
		values.put(KEY_NICKNAME, user.getNickname());
		values.put(KEY_TOTALPOINTS, user.getTotalpoints());
		values.put(KEY_TOTALCACHES, user.getTotalcaches());
		values.put(KEY_TEAMCOLOR, user.getTeam());
		values.put(KEY_PASSWORD, user.getPassword());

		// Inserting Row
		db.insert(TABLE_USER, null, values);
		Log.i("MAIN", user.toString());
		db.close(); // Closing database connection
	}

	// Getting All Contacts
	public void getUser() {
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_USER;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if ((cursor != null) && cursor.moveToFirst()) {
			User user = new User();
			user.setId(Integer.parseInt(cursor.getString(0)));
			user.setUsername(cursor.getString(1));
			user.setNickname(cursor.getString(2));
			user.setTotalcaches(Integer.parseInt(cursor.getString(3)));
			user.setTotalpoints(Integer.parseInt(cursor.getString(4)));
			user.setTeam(cursor.getString(5));
			user.setPassword(cursor.getString(6));
			Log.i("MAIN", user.toString());
			DataClass.user = user;
		} else {
			Log.i("MAIN", "no database");
		}
		db.close();
	}

	public void remove() {
		cx.deleteDatabase(DATABASE_NAME);
	}

}
