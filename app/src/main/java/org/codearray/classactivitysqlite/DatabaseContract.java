package org.codearray.classactivitysqlite;

import android.provider.BaseColumns;

public final class DatabaseContract {

	// To prevent someone from accidentally instantiating the contract class,
	// give it an empty constructor.
	public DatabaseContract() {}

 	/* Inner class that defines the table contents */
	public static abstract class Users implements BaseColumns {
		public static final String TABLE_NAME = "users";
		public static final String COL_FULLNAME = "fullname";
		public static final String COL_EMAIL = "email";
	}

}
