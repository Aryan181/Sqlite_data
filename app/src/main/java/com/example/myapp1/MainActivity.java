package com.example.myapp1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jovanovic.stefan.sqlitetutorial.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class );
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, book_id,book_title,book_author,book_pages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    void storeDataInArrays()
    {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "no data",Toast.LENGTH_LONG);
        }
        else
        {
             while(cursor.moveToNext())
             {
                 book_id.add(cursor.getString(0));
                 book_title.add(cursor.getString(1));
                 book_author.add(cursor.getString(2));
                 book_pages.add(cursor.getString(3));
             }
        }
    }

    public String dataLoader()
    {
        ArrayList<String> locations = new ArrayList<>();
        locations.add("1-2");
        locations.add("3-4");
        return locations.get(1);
    }

    void listGenerator()
    {
        for(int i = 1;i<=3;i++)
        {

        }
    }


}