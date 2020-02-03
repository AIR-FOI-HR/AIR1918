package com.example.tableview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.core.interfaces.DataPresenter;
import com.example.core.items.HipItem;
import com.example.core.items.NeckItem;
import com.example.core.items.WaistItem;
import com.example.core.items.WeightItem;

import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class TableModule extends Fragment implements DataPresenter {
    private boolean moduleReadyFlag = false;

    String[] tableHeader;
    String[][] tableContent;
    TableView<String[]> tableView;
    View currentView;
    private List<HipItem> hipItemList;
    private List<WeightItem> weightItemList;
    private List<NeckItem> neckItemList;
    private List<WaistItem> waistItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tableHeader = new String[]{"Date","Value"};


        tableView = view.findViewById(R.id.tableview);
        currentView = view;

        tableView.setHeaderBackgroundColor(Color.GREEN);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this.getContext(),tableHeader));
        tableView.setColumnCount(2);

        Button buttonWeight = view.findViewById(R.id.tableButton1);
        Button buttonWaist = view.findViewById(R.id.tableButton2);
        Button buttonHips = view.findViewById(R.id.tableButton3);
        Button buttonNeck = view.findViewById(R.id.tableButton4);


        buttonWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayWeightData();
            }
        });

        buttonWaist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayWaistData();
            }
        });

        buttonHips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayHipsData();
            }
        });

        buttonNeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNeckData();
            }
        });


    }

    private void displayWeightData()
    {
        tableContent = new String[weightItemList.size()][2];


        for(int i=0;i<2;i++)
        {
            for(int j=0;j<weightItemList.size();j++)
            {
                if(i==0)
                {
                    tableContent[j][i] = weightItemList.get(j).getDateValue();
                }
                else
                {
                    tableContent[j][i] = weightItemList.get(j).getWeightValue();
                }
            }
        }

        tableView.setDataAdapter(new SimpleTableDataAdapter(this.getContext(), tableContent));
    }

    private void displayWaistData()
    {
        tableContent = new String[waistItemList.size()][2];


        for(int i=0;i<2;i++)
        {
            for(int j=0;j<waistItemList.size();j++)
            {
                if(i==0)
                {
                    tableContent[j][i] = waistItemList.get(j).getDateValue();
                }
                else
                {
                    tableContent[j][i] = waistItemList.get(j).getWaistValue();
                }
            }
        }

        tableView.setDataAdapter(new SimpleTableDataAdapter(this.getContext(), tableContent));
    }

    private void displayHipsData()
    {
        tableContent = new String[hipItemList.size()][2];


        for(int i=0;i<2;i++)
        {
            for(int j=0;j<hipItemList.size();j++)
            {
                if(i==0)
                {
                    tableContent[j][i] = hipItemList.get(j).getDateValue();
                }
                else
                {
                    tableContent[j][i] = hipItemList.get(j).getHipValue();
                }
            }
        }

        tableView.setDataAdapter(new SimpleTableDataAdapter(this.getContext(), tableContent));
    }

    private void displayNeckData()
    {
        tableContent = new String[neckItemList.size()][2];


        for(int i=0;i<2;i++)
        {
            for(int j=0;j<neckItemList.size();j++)
            {
                if(i==0)
                {
                    tableContent[j][i] = neckItemList.get(j).getDateValue();
                }
                else
                {
                    tableContent[j][i] = neckItemList.get(j).getNeckValue();
                }
            }
        }

        tableView.setDataAdapter(new SimpleTableDataAdapter(this.getContext(), tableContent));
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
        return "Table view";
    }

    @Override
    public void setWeightData(List<WeightItem> weights) {
        this.weightItemList = weights;
    }

    @Override
    public void setHipData(List<HipItem> hips) {
        this.hipItemList = hips;
    }

    @Override
    public void setNeckData(List<NeckItem> necks) {
        this.neckItemList = necks;
    }

    @Override
    public void setWaistData(List<WaistItem> waists) {
        this.waistItemList = waists;
    }


}
