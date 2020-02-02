package com.example.tableview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.core.interfaces.DataPresenter;

import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class TableModule extends Fragment implements DataPresenter {
    private boolean moduleReadyFlag = false;

    String[] spaceProbeHeaders;
    String[][] spaceProbes;
    TableView<String[]> tableView;
    View currentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO
        // Logika za modul ovdje
        spaceProbeHeaders = new String[]{"No", "Name", "Nesto1", "Destination"};
        spaceProbes = new String[][]{
                {"1", "Pioneer", "Chemical", "Jupiter"},
                {"2", "Voyager", "Plasma", "Andromeda"},
                {"3", "Casini", "Solar", "Saturn"},
                {"4", "Spitzer", "Anti-Matter", "Andromeda"},
                {"5", "Appolo", "Chemical", "Moon"},
                {"6", "Curiosity", "Solar", "Mars"},
        };

        tableView = view.findViewById(R.id.tableview);
        currentView = view;

        //funkcija za postavke

        moduleReadyFlag = true;
        tryToDisplayData();


    }

    private void tryToDisplayData() {
        if(moduleReadyFlag)
        {
            displayData();
        }
    }

    private void displayData() {
        tableView.setHeaderBackgroundColor(Color.BLUE);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this.getContext(),spaceProbeHeaders));
        tableView.setColumnCount(4);

        tableView.setDataAdapter(new SimpleTableDataAdapter(this.getContext(), spaceProbes));
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public Drawable getIcon(Context context) {
        return context.getDrawable(R.drawable.icon_tables);
    }

    @Override
    public String getName(Context context) {
        return "Table module";
    }

    @Override
    public void setWeightData(List<String> weights) {

    }

    @Override
    public void setHipData(List<String> hips) {

    }

    @Override
    public void setNeckData(List<String> necks) {

    }

    @Override
    public void setWaistData(List<String> waists) {

    }
}
