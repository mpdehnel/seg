package com.data;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseCacheHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "cachesManager";
	private static final String TABLE_CACHES = "caches";
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_DISCRIPTION = "discription";
	private static final String KEY_LONGITUDE = "longitude";
	private static final String KEY_LATITUDE = "latitude";
	private static final String KEY_FOUNDED = "founded";
	private static final String KEY_TEAMCOLOR = "TEAMCOLOR";
	private Context cx;

	public DatabaseCacheHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.cx = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CACHES_TABLE = "CREATE TABLE " + TABLE_CACHES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_DISCRIPTION + " TEXT," + KEY_LONGITUDE + " TEXT,"
				+ KEY_LATITUDE + " TEXT," + KEY_FOUNDED + " TEXT,"
				+ KEY_TEAMCOLOR + " TEXT" + ")";
		db.execSQL(CREATE_CACHES_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CACHES);

		// Create tables again
		onCreate(db);
	}

	public void addCache(Cache cache) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, cache.getName()); // Contact Name
		values.put(KEY_DISCRIPTION, cache.getDescripton());
		values.put(KEY_FOUNDED, cache.isFound());
		values.put(KEY_LATITUDE, cache.getGeopoint().getLatitudeE6());
		values.put(KEY_LONGITUDE, cache.getGeopoint().getLongitudeE6());
		values.put(KEY_TEAMCOLOR, cache.getTeamcolour());

		// Inserting Row
		db.insert(TABLE_CACHES, null, values);
		db.close(); // Closing database connection
	}

	// Getting All Contacts
	public void getAllCache() {
		List<Cache> cachlist = new ArrayList<Cache>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CACHES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Cache cache = new Cache();
				cache.setID(Integer.parseInt(cursor.getString(0)));
				cache.setName(cursor.getString(1));
				cache.setDescripton(cursor.getString(2));
				cache.setGeopoint(new GeoPoint(Integer.parseInt(cursor
						.getString(4)), Integer.parseInt(cursor.getString(3))));
				cache.setfounded(setBoolean(cursor.getString(5)));
				cache.setTeamcolor(cursor.getString(6));

				cachlist.add(cache);
			} while (cursor.moveToNext());
		}
		Cache[] tmp = new Cache[cachlist.size()];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = cachlist.get(i);
		}
		DataClass.addCachesFromDataBase(tmp);
		db.close();
	}

	private Boolean setBoolean(String isfound) {
		boolean b;
		if (isfound.equals("1"))
			b = true;
		else
			b = false;
		return b;
	}

	public void remove() {
		cx.deleteDatabase(DATABASE_NAME);

	}

}
