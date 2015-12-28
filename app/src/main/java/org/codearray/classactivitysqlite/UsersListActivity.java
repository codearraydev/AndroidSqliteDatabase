package org.codearray.classactivitysqlite;

import org.codearray.classactivitysqlite.DatabaseContract.Users;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class UsersListActivity extends AppCompatActivity {
    public DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        // Show the Up button in the action bar.
       // setupActionBar();


        List<String> usersList = new ArrayList<String>();

        dbHelper=new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] columns = { Users._ID, Users.COL_FULLNAME, Users.COL_EMAIL };
        // How you want the results sorted in the resulting Cursor
        String sortOrder = Users._ID + " ASC";

        Cursor c = db.query(Users.TABLE_NAME, columns, null, null, null, null,  sortOrder);

        // looping through all rows and adding to list
        while (c.moveToNext()) {
            usersList.add(c.getString(0) + ": " + c.getString(1) + "("+ c.getString(2) +")");
        }
        c.close();

        ListView listview=(ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, usersList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                TextView txt=(TextView) v;
                int sep = txt.getText().toString().indexOf(":");
                String rid = txt.getText().toString().substring(0, sep);
                ManageUser(rid);
            }
        });

        db.close();

    }

    public void ManageUser(String id) {
        Toast.makeText(this, "Manage Record: " + id, Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(this, ManageUserActivity.class);
        intent.putExtra("recordID",id);
        startActivity(intent);
        finish();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    private void setupActionBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            getActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.users_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
