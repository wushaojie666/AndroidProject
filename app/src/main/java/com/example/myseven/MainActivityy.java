package com.example.myseven;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SmallLetters
 */
public class MainActivityy extends AppCompatActivity {

    private ListView mlist_view;
    private ArrayAdapter<String> mAdapter;
    private List<String> mContatcList     = new ArrayList<>();
    public static final int                  REQ_CODE_CONTACT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAdapter();
        checkContactPermission();

    }

    /**
     * 检查申请联系人权限
     */
    private void checkContactPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            //未获取到读取联系人权限

            //向系统申请权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, REQ_CODE_CONTACT);
        } else {
            query();
        }
    }

    private void query() {
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        Log.d("SmallLetters", ContactsContract.CommonDataKinds.Phone.CONTENT_URI.toString());
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                mContatcList.add("name" + name + "number" + number);
            }
            //更新数据
            mAdapter.notifyDataSetChanged();
            cursor.close();

        }
    }


    private void initAdapter() {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mContatcList);

        mlist_view.setAdapter(mAdapter);
    }

    private void initView() {
        //获取list控件
        mlist_view = findViewById(R.id.contacts_view);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //判断用户是否，同意 获取联系人授权
        if (requestCode == REQ_CODE_CONTACT && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //获取到读取联系人权限
            query();
        } else {
            Toast.makeText(this, "未获取到联系人权限", Toast.LENGTH_SHORT).show();
        }
    }
}
