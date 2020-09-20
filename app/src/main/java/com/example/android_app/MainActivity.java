package com.example.android_app;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.android_app.data.AppDatabase;
import com.example.android_app.data.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_alarm, R.id.navigation_settings, R.id.navigation_workout,
                R.id.navigation_stats, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        appDatabase = AppDatabase.getInstance(MainActivity.this);

        user = new User("Kovács", "Sámuel");

        // ez nem legális, mert blokkolja a main thread-et
        //appDatabase.getUserDao().insert(user);

        // create worker thread to insert data into database
        new InsertTask(MainActivity.this, user).execute();

    }


    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<MainActivity> activityReference;
        private User user;

        // only retain a weak reference to the activity
        InsertTask(MainActivity context, User user) {
            activityReference = new WeakReference<>(context);
            this.user = user;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {
            activityReference.get().appDatabase.getUserDao().insert(user);
            return true;
        }


        // onPostExecute runs on main thread
        /*
        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool){
                //activityReference.get().setResult(note,1);
            }
        }
        */

    }

}