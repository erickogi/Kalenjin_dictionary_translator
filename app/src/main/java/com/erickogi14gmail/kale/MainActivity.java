package com.erickogi14gmail.kale;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.erickogi14gmail.kale.Dictionary.DbOperation;
import com.erickogi14gmail.kale.Learn.Learn;
import com.erickogi14gmail.kale.Notes.FragmentNotes;
import com.erickogi14gmail.kale.Search.FragmentFavorites;
import com.erickogi14gmail.kale.Search.FragmentSearch;
import com.erickogi14gmail.kale.Search.ResultsActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  toolbar.setLogo(R.drawable.ic_library_books_black_24dp);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment = new FragmentSearch();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, "fragmentSearch").commit();
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                //viewReceiptsMenu=findViewById(R.id.nav_Word_of_the_day);


            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_Word_of_the_day) {
            Random random=new Random();
            int rand=random.nextInt(19-1)+1;
            DbOperation dbOperation=new DbOperation(MainActivity.this);

            //int pos=listPojos.get(position).getEntry_id();
            Intent intent=new Intent(MainActivity.this,ResultsActivity.class);
            intent.putExtra("position",rand);
            intent.putExtra("word","Words");
            startActivity(intent);

          //  final SearchView search = (SearchView) findViewById(R.id.search_bar);
            //insertItem();


//            search.setQuery("",false);
//            search.clearFocus();
//
//
//            RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relative_layout_search);
//            relativeLayout.setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }
    private DbOperation dbOperation;


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

            if(id==R.id.nav_favoirte){
            fragment=new FragmentFavorites();
            Log.d("fav_cliked","clicked");
            popOutFragments();
            setUpView();
        }else if(id==R.id.nav_home){
                popOutFragments();
            }else if(id==R.id.nav_notes){
                fragment=new FragmentNotes();
                popOutFragments();
                setUpView();
            }else if(id==R.id.nav_learn){
                startActivity(new Intent(MainActivity.this, Learn.class));
            }else if(id==R.id.nav_share){
            shareEvent("");
            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void shareEvent(String text) {
        Intent in = new Intent();
        in.setAction(Intent.ACTION_SEND);
        in.putExtra(Intent.EXTRA_TEXT, "Teach yourself some kalenjin..The Language of champions (Download app @ www.erickogi.co.ke ");
        in.setType("text/plain");
        startActivity(in);
    }
    void setUpView() {
        if (fragment != null) {
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment)
                    .addToBackStack(null).commit();
        }

    }

    void popOutFragments() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }
}
