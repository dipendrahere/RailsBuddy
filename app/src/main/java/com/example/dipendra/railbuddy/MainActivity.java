package com.example.dipendra.railbuddy;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dipendra.railbuddy.features.TrainRoute;
import com.example.dipendra.railbuddy.features.trainsBetweenStations;
import com.example.dipendra.railbuddy.fragments.FareEnquiry;
import com.example.dipendra.railbuddy.fragments.LiveStatus;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    LiveStatus liveStatus;
    FareEnquiry fareEnquiry;
    trainsBetweenStations trainsBetweenStations;
    TrainRoute trainRoute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "Please turn on mobile data or wifi", Toast.LENGTH_LONG).show();
        }
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.nav_camera) {
            liveStatus = new LiveStatus();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, liveStatus).commit();
        } else if (id == R.id.nav_gallery) {
            intent = new Intent(this, TrainRoute.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            fareEnquiry = new FareEnquiry();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, fareEnquiry).commit();

        } else if (id == R.id.nav_manage) {
            intent = new Intent(this, trainsBetweenStations.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
