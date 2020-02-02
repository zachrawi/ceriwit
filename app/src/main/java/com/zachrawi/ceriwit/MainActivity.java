package com.zachrawi.ceriwit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        if (ParseUser.getCurrentUser() == null) {
            menuInflater.inflate(R.menu.menu_main_notlogin, menu);
        } else {
            menuInflater.inflate(R.menu.menu_main_loggedin, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuRegister) {
            Intent intent = new Intent(this, RegisterActivity.class);

            startActivity(intent);
        } else if (item.getItemId() == R.id.menuLogin) {
            Intent intent = new Intent(this, LoginActivity.class);

            startActivity(intent);
        } else if (item.getItemId() == R.id.menuLogout) {
            ParseUser.logOut();

            // reload
            finish();
            startActivity(getIntent());
        } else if (item.getItemId() == R.id.menuPost) {
            Intent intent = new Intent(this, PostActivity.class);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
