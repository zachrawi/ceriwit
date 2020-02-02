package com.zachrawi.ceriwit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    private ArrayList<Ceriwit> mCeriwits;
    private CeriwitAdapter mCeriwitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mCeriwits = new ArrayList<>();

        mCeriwitAdapter = new CeriwitAdapter(this, R.layout.item_ceriwit, mCeriwits);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mCeriwitAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        loadCeriwits();
    }

    private void loadCeriwits() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ceriwit");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    mCeriwits.clear();

                    for (int i=0;i<objects.size();i++) {
                        ParseObject parseObject = objects.get(i);

                        try {
                            ParseUser user = parseObject.getParseUser("user");
                            user.fetchIfNeeded();
                            String username = user.getUsername();
                            String message = parseObject.getString("message");

                            mCeriwits.add(new Ceriwit(parseObject.getObjectId(), username, message));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCeriwitAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
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
