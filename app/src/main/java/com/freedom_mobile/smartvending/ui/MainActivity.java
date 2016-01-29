package com.freedom_mobile.smartvending.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.freedom_mobile.smartvending.R;
import com.freedom_mobile.smartvending.adapters.ProductAdapter;
import com.freedom_mobile.smartvending.model.Product;
import com.freedom_mobile.smartvending.utils.RecyclerTouchListener;
import com.freedom_mobile.smartvending.utils.SpacesItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int PORTRAIT_MODE = 2;
    public static final int LANDSCAPE_MODE = 3;
    private Context mContext;

    @Bind(R.id.productRecyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String[] placeNameArray = {"Arduino", "Pin Famale", "Stackable", "LCD Screen", "Xbee", "NFC RFID"};

        for (int i = 0; i < placeNameArray.length; i++) {
            Product.addProduct(new Product(
                            String.valueOf(i),
                            placeNameArray[i],
                            placeNameArray[i].replaceAll("\\s+", "").toLowerCase(),
                            "Product name: " + placeNameArray[i],
                            1.0
                    )
            );
        }

        float recyclerViewSpacing = getResources().getDimension(R.dimen.recyclerViewPadding);

        mRecyclerView.addItemDecoration(new SpacesItemDecoration((int) recyclerViewSpacing));
        mRecyclerView.setHasFixedSize(true);
        if (this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, PORTRAIT_MODE));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, LANDSCAPE_MODE));
        }
        mRecyclerView.setAdapter(new ProductAdapter(this, Product.productHashMap));

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent detailIntent = new Intent(mContext, DetailActivity.class);
                        detailIntent.putExtra("id", String.valueOf(position));
                        startActivity(detailIntent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}