package com.cdh.mynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.cdh.mynote.list.MyAdapter;
import com.cdh.mynote.list.Stuff;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Hello Asd!", Toast.LENGTH_SHORT).show();

        startService(new Intent(this, ServiceChatHead.class));

        Stuff[] stuffs = {
                new Stuff("nihao", "09/08"),
                new Stuff("hello", "09/08"),
                new Stuff("ola", "09/08"),
                new Stuff("????", "09/08"), };


        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new MyAdapter(this, stuffs));
    }
}
