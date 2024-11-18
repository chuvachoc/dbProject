package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class edit_book extends AppCompatActivity {

    private EditText editTextBookName, editTextBookAuthor;
    private Button buttonUpdate, buttonDelete;
    private DataBaseHelper dbHelper;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        editTextBookName = findViewById(R.id.editTextBookName);
        editTextBookAuthor = findViewById(R.id.editTextBookAuthor);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        dbHelper = new DataBaseHelper(this);

        Intent intent = getIntent();
        String bookName = intent.getStringExtra("name_book");
        String bookAuthor = intent.getStringExtra("Book_author");
        bookId = intent.getIntExtra("book_id", -1);

        editTextBookName.setText(bookName);
        editTextBookAuthor.setText(bookAuthor);

        buttonUpdate.setOnClickListener(v -> updateBook());

        buttonDelete.setOnClickListener(v -> deleteBook());
    }

    private void updateBook() {
        String newBookName = editTextBookName.getText().toString();
        String newBookAuthor = editTextBookAuthor.getText().toString();
        int rowsUpdated = dbHelper.updateBookById(bookId, newBookName, newBookAuthor);

        if (rowsUpdated > 0) {
            Toast.makeText(this, "Книга обновлена", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Не удалось обновить книгу", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteBook() {
        int rowsDeleted = dbHelper.deleteBookById(bookId);

        if (rowsDeleted > 0) {
            Toast.makeText(this, "Книга удалена", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Не удалось удалить книгу", Toast.LENGTH_SHORT).show();
        }
    }
}
