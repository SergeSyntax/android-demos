package com.sergway.contactmanager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sergway.contactmanager.data.DatabaseHandler;
import com.sergway.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        contactArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(this);

//        db.addContact(new Contact("James", "4643643"));
//        db.addContact(new Contact("Greg", "45754"));
//        db.addContact(new Contact("Helena", "43642642"));
//        db.addContact(new Contact("Carimo", "547653642"));
//        db.addContact(new Contact("Raswan", "5425"));
//        db.addContact(new Contact("Raswan", "34643"));
//        db.addContact(new Contact("Rogerio", "4364"));
//        db.addContact(new Contact("Khendo", "2466246"));
//        db.addContact(new Contact("Sarai", "747537422"));
//        db.addContact(new Contact("Ken", "537347427"));
//        db.addContact(new Contact("Antonio", "2467537"));
//        db.addContact(new Contact("Silo", "142341"));
//        db.addContact(new Contact("Santos", "5875959"));
//        db.addContact(new Contact("Litos", "4364642"));
//        db.addContact(new Contact("Karate", "214123431"));
//        db.addContact(new Contact("Guerra", "89706809"));
//        db.addContact(new Contact("Gema", "657865"));


        List<Contact> contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            contactArrayList.add(contact.getName());
        }

        // create array adapter
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                contactArrayList);

        // add to our list view
        listView.setAdapter(arrayAdapter);

//        Log.d("Main", "onCreate: "+contact.getName());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, contactArrayList.get(position), Toast.LENGTH_SHORT).show();
        });

    }
}