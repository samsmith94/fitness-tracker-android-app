package com.example.android_app.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

    private static AppDatabase appDatabase;

    public static  AppDatabase getInstance(Context context) {
        if (null == appDatabase) {
            appDatabase = buildDatabaseInstance(context);
        }
        return appDatabase;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "fitness-tracker")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public void cleanUp() {
        appDatabase = null;
    }
}
