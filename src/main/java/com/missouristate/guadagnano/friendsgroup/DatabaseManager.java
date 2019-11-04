package com.missouristate.guadagnano.friendsgroup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "friendDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FRIENDS = "friend";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //builds sql create statement
        String sqlCreate = "create table " + TABLE_FRIENDS + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + NAME;
        sqlCreate += " text, " + EMAIL + " text )";

        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drops old table when it exists
        db.execSQL("drop table if exists " + TABLE_FRIENDS);

        //Re-create the tables
        onCreate(db);

    }

    public void insert(Friends friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_FRIENDS;
        sqlInsert += " values( null, '" + friend.getName();
        sqlInsert += "', '" + friend.getEmail() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Friends> selectAll() {
        String sqlQuery = "select * from " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Friends> friend = new ArrayList<>();
        while (cursor.moveToNext()) {
            Friends currentFriend
                    = new Friends(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
            friend.add(currentFriend);
        }
        db.close();
        return friend;
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_FRIENDS;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int id, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update " + TABLE_FRIENDS;
        sqlUpdate += " set " + NAME + " = '" + name + "', ";
        sqlUpdate += EMAIL + " = '" + email + "'";
        sqlUpdate += " where " + ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }
}
