package org.codearray.classactivitysqlite;

import org.codearray.classactivitysqlite.DatabaseContract.Users;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "EmailUser.db";

	private static final String CREATE_TBL_USERS = "CREATE TABLE "
			+ Users.TABLE_NAME + " (" + Users._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + Users.COL_FULLNAME
			+ " TEXT NOT NULL, " + Users.COL_EMAIL + " TEXT)";


	public DatabaseHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TBL_USERS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub 
	}
}
