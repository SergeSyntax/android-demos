package com.sergway.contactroom.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sergway.contactroom.data.ContactDao;
import com.sergway.contactroom.model.Contact;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile ContactRoomDatabase INSTANCE;
    public static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                ContactDao contactDao = INSTANCE.contactDao();
                contactDao.deleteAll();
                Contact contact = new Contact("Sergway", "Teacher");
                contactDao.insert(contact);
                contact = new Contact("bond", "spy");
                contactDao.insert(contact);
                contact = new Contact("Bruce", "Fighter");
                contactDao.insert(contact);
            });
        }
    };

    public static ContactRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_database"
                    ).addCallback(sRoomDatabaseCallback).build();
            }
        }
        return INSTANCE;
    }
    public abstract ContactDao contactDao();
}
