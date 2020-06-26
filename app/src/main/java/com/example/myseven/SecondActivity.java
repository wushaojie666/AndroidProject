package com.example.myseven;



import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    private String newId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button_insert = findViewById(R.id.insert);
        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.myseven.provider/Contacts");
                ContentValues values = new ContentValues();
              ;
                values.put("name", "海明威");
                values.put("phone",345);
                values.put("sex","男");
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button button_delete = findViewById(R.id.delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.myseven.provider/Contacts/"+newId);
                getContentResolver().delete(uri, null, null);
            }
        });

        Button button_update = findViewById(R.id.update);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.myseven.provider/Contacts/"+newId);
                ContentValues values = new ContentValues();
                values.put("name", "绿野仙踪");
                values.put("phone", 666);
                values.put("sex", "女");
                getContentResolver().update(uri, values, null, null);
            }
        });

        Button button_query = findViewById(R.id.query);

        button_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<InfoBean> contactList = new ArrayList<InfoBean>();
                Uri uri = Uri.parse("content://com.example.myseven.provider/Contacts");
                Cursor cursor = getContentResolver().query(uri, null, null, null,null);
                if(cursor != null){
                    while (cursor.moveToNext()){

                        InfoBean info=new InfoBean();
                        info . id = cursor.getInt(cursor.getColumnIndex("id"));
                        info. name = cursor.getString(cursor.getColumnIndex("name"));
                        info. phone = cursor.getString(cursor.getColumnIndex("phone"));
                        info. sex = cursor.getString(cursor.getColumnIndex("sex"));

                        contactList.add(info);

                        /*Log.d(TAG, "id："+id);
                        Log.d(TAG, "名字："+name);
                        Log.d(TAG, "电话："+phone);
                        Log.d(TAG, "性别："+sex);*/
                    }

                }
                QueryAdapter adapter=new QueryAdapter(SecondActivity.this,R.layout.item_sql,contactList);
                ListView listview=(ListView)findViewById(R.id.view);
                listview.setAdapter(adapter);
            }

        });
    }
}

