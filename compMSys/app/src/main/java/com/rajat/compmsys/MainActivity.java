package com.rajat.compmsys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rajat.compmsys.Volley.VolleyClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static SharedPreferences sharedpreferences;
    public static SharedPreferences.Editor editor ;
    TextView user_id,category;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
       // getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).getString("userid","");
        i=getIntent();
        editor = sharedpreferences.edit();
        MainActivity.editor.putString("token", i.getStringExtra("token"));
        MainActivity.editor.putString("email", i.getStringExtra("email"));
        MainActivity.editor.putString("id", i.getStringExtra("id"));
        MainActivity.editor.putString("hostel", i.getStringExtra("hostel"));
        MainActivity.editor.putString("category", i.getStringExtra("category"));
        MainActivity.editor.putString("whocreated",i.getStringExtra("whocreated"));
        MainActivity.editor.apply();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        if(i.getStringExtra("category").equals("StudentAdmin")){
            nav_Menu.findItem(R.id.all_complaints).setVisible(true);
            nav_Menu.findItem(R.id.add_user).setVisible(true);
        }
        else if(i.getStringExtra("category").equals("Warden")||i.getStringExtra("category").equals("Dean")){
            nav_Menu.findItem(R.id.my_complaints).setVisible(false);
            nav_Menu.findItem(R.id.subscribe).setVisible(false);
            nav_Menu.findItem(R.id.all_complaints).setVisible(true);
            nav_Menu.findItem(R.id.add_user).setVisible(true);
        }
        View header = navigationView.getHeaderView(0);
        user_id = (TextView) header.findViewById(R.id.user_id);
        user_id.setText(getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).getString("email",""));
        category =(TextView)header.findViewById(R.id.category);
        category.setText(getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).getString("category",""));
        navigationView.setNavigationItemSelectedListener(this);
        if(sharedpreferences.getString("category","").equals("Dean")) VolleyClick.solverComplaintsClick(getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).getString("category",""),MainActivity.this);
            else VolleyClick.myComplaintsClick(getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).getString("id",""),MainActivity.this);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_complaints) {
          VolleyClick.myComplaintsClick(getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).getString("id",""),MainActivity.this);
           /* listview fragment = new listview();
            Bundle b=new Bundle();
            b.putInt("id",0);
           // b.putParcelableArrayList("allcomplaints",complaintObjList);
            fragment.setArguments(b);
            //fragment.setArguments();*/

         /*   android.support.v4.app.FragmentTransaction fragmentTransaction =
                    this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,fragment);
            fragmentTransaction.commit();*/
        } else if (id == R.id.notifcation) {
            notification fragment = new notification();
           /* Bundle b=new Bundle();
            b.putInt("id",0);
            fragment.setArguments(b);
            //fragment.setArguments();*/

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.search_complaints) {
            search fragment = new search();
          /*  Bundle b=new Bundle();
            b.putInt("id",1);
            fragment.setArguments(b);
            //fragment.setArguments();*/

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.subscribe) {
            subscribe fragment = new subscribe();
          /*  Bundle b=new Bundle();
            b.putInt("id",1);
            fragment.setArguments(b);
            //fragment.setArguments();*/

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.logout) {
           // VolleyClick.onLogoutClick(MainActivity.this);
            MainActivity.editor = MainActivity.sharedpreferences.edit();
            MainActivity.editor.putString("token", "");
            MainActivity.editor.apply();
            Intent openH = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(openH);

        } else if (id == R.id.add_user) {
            addnewuser fragment = new addnewuser();
          /*  Bundle b=new Bundle();
            b.putInt("id",1);
            fragment.setArguments(b);
            //fragment.setArguments();*/

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,fragment);
            fragmentTransaction.commit();

        }else if(id==R.id.all_complaints){
            VolleyClick.solverComplaintsClick(getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).getString("category",""),MainActivity.this);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
