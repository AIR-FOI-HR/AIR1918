package hr.foi.a2fit4u.managers;

import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.chartview.ChartModule;
import com.example.core.interfaces.DataPresenter;
import com.example.core.managers.DataManager;
import com.example.tableview.TableModule;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import hr.foi.a2fit4u.R;

public class DataPresenterManager {
    private static final DataPresenterManager ourInstance = new DataPresenterManager();
    List<DataPresenter> modules;
    private AppCompatActivity activity;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private int dynamicGroupId;

    public static DataPresenterManager getInstance() {
        return ourInstance;
    }

    private DataPresenterManager() {
        modules = new ArrayList<>();
        modules.add(new ChartModule());
        modules.add(new TableModule());
    }

    public void setDrawerDependencies(AppCompatActivity activity, NavigationView navigationView, DrawerLayout drawerLayout, int dynamicGroupId)
    {

        this.activity = activity;
        this.navigationView = navigationView;
        this.drawerLayout = drawerLayout;
        this.dynamicGroupId = dynamicGroupId;

        setupDrawerMenu();
    }

    private void setupDrawerMenu() {
        for (int i = 0; i < modules.size(); i++)
        {
            DataPresenter module = modules.get(i);
            navigationView.getMenu()
                    .add(dynamicGroupId, i, i+1, module.getName(activity))
                    .setIcon(module.getIcon(activity))
                    .setCheckable(true);
        }
    }

    public void startMainModule()
    {
        //DataPresenter mainModule = modules != null ? modules.get(0) : null;
        //if (mainModule != null)
        //   startModule(mainModule);
    }

    private void startModule(DataPresenter module)
    {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_fragment, module.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

        DataManager.getInstance().sendData(module);
    }

    public void selectNavigationItem(MenuItem menuItem)
    {
        if (!menuItem.isChecked())
        {
            menuItem.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);

            DataPresenter module = modules.get(menuItem.getItemId());
            startModule(module);
        }
    }
}
