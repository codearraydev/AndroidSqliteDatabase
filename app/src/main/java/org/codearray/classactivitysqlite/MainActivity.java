package org.codearray.classactivitysqlite;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.codearray.classactivitysqlite.DatabaseContract.Users;

public class MainActivity extends AppCompatActivity {
    public DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new DatabaseHelper(this);
    }



    public void AddRecord(View view) {
        EditText fullname=(EditText) findViewById(R.id.editTextFullName);
        EditText email=(EditText) findViewById(R.id.editTextEmail);

        String val1=fullname.getText().toString();
        String val2=email.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Users.COL_FULLNAME, val1);
        values.put(Users.COL_EMAIL, val2);

        long newRowId = db.insert(Users.TABLE_NAME, null, values);
        if (newRowId>0) {
            Toast.makeText(this, "New Record Inserted: " + newRowId, Toast.LENGTH_SHORT).show();
        }
        db.close(); // Closing database connection

    }

    public void ListAllUsers(View view) {
        EditText fullname=(EditText) findViewById(R.id.editTextFullName);
        EditText email=(EditText) findViewById(R.id.editTextEmail);

        fullname.setText("");
        email.setText("");

        Intent intent=new Intent(this, UsersListActivity.class);
        startActivity(intent);
    }

}