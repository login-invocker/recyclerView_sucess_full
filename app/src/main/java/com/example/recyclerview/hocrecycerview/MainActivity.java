package com.example.recyclerview.hocrecycerview;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.adapter.ContacAdapter;
import com.example.recyclerview.model.Contact;
import com.example.recyclerview.model.OneloadmoreLissener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Contact> contacts = new ArrayList<>();
    ContacAdapter contacAdapter;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addEvent() {
        contacAdapter.setOneloadmoreLissener(new OneloadmoreLissener() {
            @Override
            public void oneLoadmore() {
                contacts.add(null);
                contacAdapter.notifyItemInserted(contacts.size() - 1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        contacts.remove(contacts.size() - 1);
                        contacAdapter.notifyItemRemoved(contacts.size());
                        int index = contacts.size();
                        int end = index + 20;
                        for (int i = index; i < end; i++) {
                            Contact c = new Contact();
                            c.setName("name " + i);
                            String phone = "098";
                            int x = random.nextInt(3);
                            if (x == 1) phone = "090";
                            else if (x == 2) phone = "094";
                            for (int p = 0; p < 7; p++) phone += random.nextInt(10);
                            c.setPhonr(phone);
                            contacts.add(c);
                        }
                        contacAdapter.notifyDataSetChanged();
                        contacAdapter.setLoaded();
                    }
                }, 5000);
            }
        });
    }

    private void addControl() {
        recyclerView = (RecyclerView) findViewById(R.id.rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contacts = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Contact c = new Contact();
            c.setName("name " + i);
            String phone = "098";
            int x = random.nextInt(3);
            if (x == 1) phone = "090";
            else if (x == 2) phone = "094";
            for (int p = 0; p < 7; p++) phone += random.nextInt(10);
            c.setPhonr(phone);
            contacts.add(c);
        }

        contacAdapter = new ContacAdapter(this, recyclerView, contacts);
        recyclerView.setAdapter(contacAdapter);
    }
}
