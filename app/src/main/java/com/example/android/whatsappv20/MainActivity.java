package com.example.android.whatsappv20;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    FloatingActionButton fab;

    //Sample List
    ListView listView;
    String[] name = {"Yash","Yash","Yash","Yash","Yash","Yash","Yash","Yash","Yash","Yash","Yash"};
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For Vibrations
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1); //Opening 2nd Fragment directly

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //Displaying Camera Image in TabLayout
        tabLayout.getTabAt(0).setIcon(R.drawable.camera_state);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabLayout.getTabAt(0).setIcon(R.drawable.camera_state);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabLayout.getTabAt(0).setIcon(R.drawable.camera_state);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Changing the Width of Camera Title
        LinearLayout layout = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = 0.5f; // e.g. 0.5f
        layout.setLayoutParams(layoutParams);

        fab = findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(80);
                Intent intent  = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        // Toolbar
        toolbar = findViewById(R.id.normal_toolbar);
        setSupportActionBar(toolbar); // setSupprotAction bar worked for the first time
    }

    // Below methods bind menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Learning SearchView Search
        MenuItem menuItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Search...");


        // This method is called when Search is in Action
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //For future use use this to search:
                 arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search_menu:
                Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
                break;
            case R.id.item1:
                Toast.makeText(this, "New Group", Toast.LENGTH_LONG).show();
                break;
            case R.id.item2:
                Toast.makeText(this, "New Broadcast", Toast.LENGTH_LONG).show();
                break;
            case R.id.item3:
                Toast.makeText(this, "WhatsApp Web", Toast.LENGTH_LONG).show();
                break;
            case R.id.item4:
                Toast.makeText(this, "Starred Messages", Toast.LENGTH_LONG).show();
                break;
            case R.id.item5:
                Toast.makeText(this, "Payments", Toast.LENGTH_LONG).show();
                break;
            case R.id.item6:
                Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show();
                break;
            default: break;
        }

        //Sample List
        listView = findViewById(R.id.sample_list);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);


        return super.onOptionsItemSelected(item);
    }
}