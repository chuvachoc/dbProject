package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Book> bookk ;
    public RecyclerViewAdapter(Context context , ArrayList<Book> bookk){
        this.context =context;
        this.bookk = bookk;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_card , parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Book book = bookk.get(position);
        holder.bookName.setText(book.getName_book());
        holder.bookAuthro.setText(book.getBook_author());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                Book clickedBook = bookk.get(clickedPosition);
                Intent intent = new Intent(context, edit_book.class);
                intent.putExtra("book_id", clickedBook.getId_book());
                intent.putExtra("name_book", clickedBook.getName_book());
                intent.putExtra("Book_author", clickedBook.getBook_author());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return  bookk.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView bookName;
        TextView bookAuthro;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.b_name);
            bookAuthro = itemView.findViewById(R.id.b_author);
        }
    }
}