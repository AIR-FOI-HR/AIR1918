package hr.foi.a2fit4u.weight;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.items.WeightItem;
import com.example.core.managers.DBManager;
import com.example.core.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import hr.foi.a2fit4u.R;

public class WeightFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<WeightItem> weightItemList;

    private float weightEntryValue;

    private TextView textViewCurrentWeight;
    private TextView textViewGoalWeight;
    private TextView textViewStartingWeight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weight,container,false);

        TextView textViewWeighIn = view.findViewById(R.id.weight_button_weighin);

        textViewCurrentWeight = view.findViewById(R.id.weight_current_weight);
        textViewGoalWeight = view.findViewById(R.id.weight_goal_weight);
        textViewStartingWeight = view.findViewById(R.id.weight_starting_weight);


        textViewWeighIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNewWeight(v);
            }
        });

        DisplayCurrentWeight();
        DisplayStartingWeight();

        return view;

    }

    private void enterNewWeight(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle(R.string.dialog_new_weight_entry);

        View viewInflated = LayoutInflater.from(v.getContext()).inflate(R.layout.weight_input, (ViewGroup) getView(), false);
        EditText input = viewInflated.findViewById(R.id.weight_dialog_input);

        builder.setView(viewInflated);

        builder.setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                weightEntryValue = Float.parseFloat(input.getText().toString());
                AddNewWeightEntry(weightEntryValue);
            }
        });

        builder.setNegativeButton(R.string.dialog_reject, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();


    }

    private void AddNewWeightEntry(float entry) {
        DBManager.getInstance().InsertData(Constants.DATA_TYPE_WEIGHT,entry);
    }

    private void DisplayCurrentWeight()
    {
        textViewCurrentWeight.setText(DBManager.getInstance().getData(Constants.DATA_TYPE_WEIGHT));
    }

    private void DisplayStartingWeight()
    {
        textViewStartingWeight.setText(DBManager.getInstance().getData(Constants.DATA_TYPE_STARTING_WEIGHT) + " KG");
    }

    //TODO
    // Display goal weight
    private void DisplayGoalWeight()
    {

    }

    private void DisplayPreviousEntries(View view)
    {
        recyclerView = view.findViewById(R.id.weight_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new WeightRecyclerAdapter(DBManager.getInstance().getData(),view.getContext());

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        DisplayPreviousEntries(view);

    }
}
