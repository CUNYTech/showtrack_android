package com.slack.cunycodes.showtrack.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.slack.cunycodes.showtrack.R;
import com.slack.cunycodes.showtrack.Helper.SessionManager;
import com.slack.cunycodes.showtrack.Helper.Utility;
import com.slack.cunycodes.showtrack.UI.Fragments.HomeFragment;
import com.slack.cunycodes.showtrack.UI.Fragments.SearchFragment;
import com.slack.cunycodes.showtrack.UI.Fragments.WatchlistFragment;

import static com.slack.cunycodes.showtrack.R.id.fab;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String LOG_TAG = getClass().getSimpleName();
    private String mUserName;
    private String mEmailAddress;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawer;
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        session = new SessionManager(this);

        if (!session.isLoggedIn()) {
            logout();
        }

        setUserNameAndEmail();
        homeFragment();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = mNavigationView.getHeaderView(0);
        TextView userNameView = (TextView) headerView.findViewById(R.id.username_nav_header);
        TextView emailView = (TextView) headerView.findViewById(R.id.email_nav_header);
        userNameView.setText(mUserName);
        emailView.setText(mEmailAddress);



        FloatingActionButton fabButton = (FloatingActionButton) findViewById(fab);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchFragment();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.navigation_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }else if(id==R.id.action_logout){
//            logout();
//        }else
        if(id == R.id.action_search){
            openSearchFragment();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            homeFragment();
        } else if (id == R.id.nav_search) {
            openSearchFragment();
        } else if (id == R.id.nav_watchlist) {
            watchListFragement();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        session.setLogin(false);
        Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void setUserNameAndEmail(){
        String encodedToken = session.getToken();
        String[] info = new String[2];
        try {
            info = Utility.decoded(encodedToken);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mUserName = info[0];
        mEmailAddress = info[1];
    }

    private void openSearchFragment(){
        SearchFragment fragment = new SearchFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void homeFragment(){
        HomeFragment fragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }



    private void watchListFragement() {
        WatchlistFragment fragment = new WatchlistFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}

