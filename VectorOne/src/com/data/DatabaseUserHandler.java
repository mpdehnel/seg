package com.data;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;

public class DatabaseUserHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "UserManager";
	private static final String TABLE_USER = "users";
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_TOTALCACHES = "totalcaches";
	private static final String KEY_TOTALPOINTS = "totalpoints";
	private static final String KEY_CURRENTPOINTS = "currentpoints";
	private static final String KEY_TEAMCOLOR = "TEAMCOLOR";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_RED = "red";
	private static final String KEY_GREEN = "green";
	private static final String KEY_BLUE = "blue";
	private static final String KEY_PURPLE = "purple";
	

	
	
	private Context cx;

	public DatabaseUserHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.cx = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_TOTALCACHES + " TEXT," + KEY_TOTALPOINTS + " TEXT,"+ KEY_CURRENTPOINTS + " TEXT,"
				+ KEY_TEAMCOLOR + " TEXT," + KEY_PASSWORD + " TEXT," + KEY_RED
				+ " TEXT," + KEY_GREEN + " TEXT," + KEY_BLUE + " TEXT,"
				+ KEY_PURPLE + " TEXT" + ")";
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
		values.put(KEY_TOTALPOINTS, user.getTotalpoints());
		values.put(KEY_TOTALCACHES, user.getTotalcaches());
		values.put(KEY_TEAMCOLOR, user.getTeam());
		values.put(KEY_PASSWORD, user.getPassword());
		values.put(KEY_CURRENTPOINTS, user.getCurrentPoints());
		values.put(KEY_BLUE, DataClass.blueportion);
		values.put(KEY_RED, DataClass.redportion);
		values.put(KEY_GREEN, DataClass.greenportion);
		values.put(KEY_PURPLE, DataClass.purpleportion);

		// Inserting Row
		db.insert(TABLE_USER, null, values);
		//Log.i("MAIN", user.toString());
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
			user.setTotalcaches(Integer.parseInt(cursor.getString(2)));
			user.setTotalpoints(Integer.parseInt(cursor.getString(3)));
			user.setCurrentPoints(Integer.parseInt(cursor.getString(3)));
			user.setTeam(cursor.getString(5));
			user.setPassword(cursor.getString(6));
			DataClass.redportion = Integer.parseInt(cursor.getString(7));
			DataClass.greenportion = Integer.parseInt(cursor.getString(8));
			DataClass.blueportion = Integer.parseInt(cursor.getString(9));
			DataClass.purpleportion = Integer.parseInt(cursor.getString(10));
			//Log.i("MAIN", user.toString());
			DataClass.user = user;
		} else {
			//Log.i("MAIN", "no database");
		}
		db.close();
	}

	public void remove() {
		cx.deleteDatabase(DATABASE_NAME);
	}

}
