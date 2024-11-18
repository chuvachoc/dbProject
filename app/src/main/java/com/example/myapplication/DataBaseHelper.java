package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static  final String DATABASE = "book_db" ;
    private static  final int SCHEMA = 2 ;
    static  final String TABLE_NAME = "book";
    public static  final String COLUMN_ID = "id_bool" ;
    public static  final String COLUMN_NAME = "book_name" ;
    public static  final String COLUMN_AUTHOR = "book_author" ;



    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE , null , SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AUTHOR + " TEXT);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long addBook(String bookName, String bookAuthor) {
        if (bookName == null || bookAuthor == null) {
            throw new IllegalArgumentException("Имя книги и автор не должны быть null");
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, bookName);
        values.put(COLUMN_AUTHOR, bookAuthor);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            throw new RuntimeException("Ошибка вставки данных в таблицу");
        }
        db.close();
        return result;
    }

    public Cursor getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }
    public int deleteBookById(long bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(bookId)});
        db.close();
        return result;
    }
    public int updateBookById(long bookId, String newBookName, String newBookAuthor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newBookName);
        values.put(COLUMN_AUTHOR, newBookAuthor);

        int result = db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(bookId)});

        db.close();

        return result;
    }



}



