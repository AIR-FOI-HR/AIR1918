package hr.foi.a2fit4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.core.CurrentActivity;
import com.example.core.managers.AccountManager;
import com.example.core.util.Util;
import com.google.android.material.navigation.NavigationView;

import hr.foi.a2fit4u.login.LoginActivity;
import hr.foi.a2fit4u.managers.DataPresenterManager;
import hr.foi.a2fit4u.measurements.MeasurementsFragment;
import hr.foi.a2fit4u.weight.WeightFragment;

import com.example.core.util.Constants;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    private Util util = new Util();

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurrentActivity.setActivity(this);
        setContentView(R.layout.activity_main);

        fragmentManager = this.getSupportFragmentManager();


        initializeLayout();
        initializeDataPresenterManager();

    }

    private void initializeLayout()
    {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
    }

    private void initializeDataPresenterManager()
    {
        DataPresenterManager dataPresenterManager = DataPresenterManager.getInstance();
        dataPresenterManager.setDrawerDependencies(this, navigationView, drawerLayout, R.id.dynamic_group);
        dataPresenterManager.startMainModule();
    }

    // mozda nepotrebno, za settingse
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

     @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.preferences:
                Intent intent = new Intent(
                                this,
                                SettingsActivity.class);
                this.startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {
            case "language":
                util.setLanguage(this);
                this.recreate();
                break;
        }
    }

    */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //staro
        /*
        drawerLayout.closeDrawer(GravityCompat.START);
        if(menuItem.getItemId() == R.id.home)
        {
            /*fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new MainFragment());

            fragmentTransaction.commit(); /

            Intent intent = new Intent(this,TableActivity.class);
            startActivity(intent);
        }
        if(menuItem.getItemId() == R.id.settings)
        {
            /*fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new FragmentSecond());

            fragmentTransaction.commit(); /
            Intent intent = new Intent(this,GraphActivity.class);
            startActivity(intent);
        }*/


        //novo
        switch(menuItem.getItemId())
        {
            //handle static cases
            case R.id.menu_profile:
                //do something
                //Intent intent = new Intent(this,TableActivity.class);
                //startActivity(intent);
                break;
            case R.id.menu_weight:
                fragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, new WeightFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("")
                        .commit();
                break;
            case R.id.menu_measurements:
                fragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, new MeasurementsFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("")
                        .commit();
                break;
            case R.id.menu_nfc:
                break;
            case Constants.NAVIGATION_SETTINGS:
                //Intent intent2 = new Intent(this,GraphActivity.class);
                //startActivity(intent2);
                //handle dynamic cases
                break;
            case Constants.NAVIGATION_LOGOUT:
                AccountManager.getInstance().logOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                DataPresenterManager.getInstance().selectNavigationItem(menuItem);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /*@Override
    public void onButtonSelected() {
        /*fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, new FragmentSecond());

        fragmentTransaction.commit(); /

        Intent intent = new Intent(this,NFCActivity.class);
        startActivity(intent);
    }*/

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
