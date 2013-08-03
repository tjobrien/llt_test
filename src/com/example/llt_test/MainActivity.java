package com.example.llt_test;

import java.util.ArrayList;



import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	private ArrayList<String> thread_ids = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Cursor cursor = getContentResolver().query(Uri.parse("content://mms-sms/conversations"), null, null, null, "normalized_date desc");
       // Cursor cursor = getContentResolver().query(Uri.parse("content://mms-sms/conversations?simple=true"), null, null, null, "normalized_date desc");
        Uri uri = Uri.parse("content://mms-sms/conversations?simple=true"); 
        ContextWrapper context = this;

		//Cursor cursor = context.getApplicationContext().getContentResolver().query(uri, null, null, null, null);
		
        Cursor cursor = getContentResolver().query(Uri.parse("content://mms"), null, null, null, "Date"); //shows all messages; sms works
        // Cursor cursor = getContentResolver().query(Uri.parse("content://sms/conversations"), null, null, null, "Date"); //causes an error bc no _id column

         Log.v("LLT", "created cursor");
         startManagingCursor(cursor);
//         cursor.moveToFirst();
        getCursorColumns(cursor);
   	  	Toast.makeText(this, "Number of Conversations: " + String.valueOf(cursor.getCount()), Toast.LENGTH_SHORT).show();
   	  	
         if (cursor != null) {
        	 if (cursor.moveToFirst()) {
                 do {
                	 thread_ids.add(cursor.getString(cursor.getColumnIndex("_id"))); 
                 } while (cursor.moveToNext());
        	 }
         }
         
         Toast.makeText(this, "Number of Threads:  " + String.valueOf(thread_ids.size()), Toast.LENGTH_LONG).show();
         
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	private void getCursorColumns(Cursor cursor) {

		try {

			if (cursor != null) {
				int num = cursor.getColumnCount();
				for (int i = 0; i < num; ++i) {
					String colname = cursor.getColumnName(i);
					Log.v("Column_Name:  ", colname);

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			//Utility.logger_D("getCursorColumns--  " + e);
		}
	}

    
}
