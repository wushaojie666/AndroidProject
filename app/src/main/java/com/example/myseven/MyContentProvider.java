package com.example.myseven;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    public static final int Contacts_DIR = 0;
    public static final int Contacts_ITEM = 1;
    public static final String AUTHORITY = "com.example.myseven.provider";
    public static UriMatcher uriMatcher;
    private MyDatabace dbhelper;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "Contacts", Contacts_DIR);
        uriMatcher.addURI(AUTHORITY, "Contacts/#", Contacts_ITEM);

    }

    @Override
    public boolean onCreate() {
        dbhelper = new MyDatabace(getContext(), "Contacts.db", null, 2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case Contacts_DIR:
                cursor = db.query("Contacts", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case Contacts_ITEM:
                String id = uri.getPathSegments().get(1);
                cursor = db.query("Contacts", projection, "id = ?", new String[]{id}, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case Contacts_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.myseven.provider.Contacts";
            case Contacts_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.myseven.provider.Contacts";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case Contacts_DIR:
            case Contacts_ITEM:
                long newBookId = db.insert("Contacts", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/Contacts/" + newBookId);
                break;

            default:
                break;
        }
        return uriReturn;
    }



    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case Contacts_DIR:
                deletedRows = db.delete("Contacts", selection, selectionArgs);
                break;
            case Contacts_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Contacts", "id = ?", new String[]{bookId});
                break;

            default:
                break;
        }
        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)) {
            case Contacts_DIR:
                updateRows = db.update("Contacts", values, selection, selectionArgs);
                break;
            case Contacts_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updateRows = db.update("Contacts", values, "id = ?", new String[]{bookId});
                break;

            default:
                break;
        }
        return updateRows;
    }


}
