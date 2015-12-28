package org.codearray.classactivitysqlite;
import org.codearray.classactivitysqlite.DatabaseContract.Users;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class ManageUserActivity extends AppCompatActivity {
    public DatabaseHelper dbHelper;
    public String rid;
    public EditText fullname, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        Intent intent=getIntent();
        rid=intent.getStringExtra("recordID");
        dbHelper=new DatabaseHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = { Users._ID, Users.COL_FULLNAME, Users.COL_EMAIL };
        Cursor c = db.query(Users.TABLE_NAME, columns, Users._ID + " = ?",
                new String[] { rid }, null, null, null, null);

        fullname=(EditText) findViewById(R.id.editFullName);
        email=(EditText) findViewById(R.id.editEmail);
        if (c != null)
        {
            c.moveToFirst();
            fullname.setText(c.getString(1));
            email.setText(c.getString(2));
        }
        c.close();
        db.close();

    }

    public void DeleteRecord(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String whereClause = Users._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] whereArgs = { rid };
        // Issue SQL statement.
        db.delete(Users.TABLE_NAME, whereClause, whereArgs);
        db.close();

        Toast.makeText(this, "Record Deleted: " + rid, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, UsersListActivity.class);
        startActivity(intent);
        finish();
    }

    public void UpdateRecord(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(Users.COL_FULLNAME, fullname.getText().toString());
        values.put(Users.COL_EMAIL, email.getText().toString());

        String whereClause=Users._ID + " = ?";
        String[] whereArgs = { rid };


        int count = db.update(Users.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
        if (count > 0)
            Toast.makeText(this, "Record Updated: " + rid, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, UsersListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manage_user, menu);
        return true;
    }

}
