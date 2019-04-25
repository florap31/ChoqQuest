package sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE = "mealquest.db";
    public static final String TABLE = "users";
    public static final String USER_ID = "ID";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASS = "pass";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE main.users (ID INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(255), pass varchar(255),type varchar(255), email varchar(255))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE);
        onCreate(db);
    }
    public long addUser(String user, String password,  String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("pass", password);
        contentValues.put("email", email);
        long res = db.insert("users", null,contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username, String password) {
        String[] columns = {USER_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = USERNAME + "=?" + " and " + PASS + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE, columns,selection,selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count>0;
    }
}
