package hr.foi.a2fit4u.measurements;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.items.MeasurementItem;
import com.example.core.managers.DBManager;
import com.example.core.util.Constants;

import java.util.List;

import butterknife.ButterKnife;
import hr.foi.a2fit4u.R;
import hr.foi.a2fit4u.weight.WeightRecyclerAdapter;

public class MeasurementsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<MeasurementItem> measurementItemList;

    private EditText inputHip;
    private EditText inputNeck;
    private EditText inputWaist;
    private EditText inputHeight;

    private TextView textViewStartingNeck;
    private TextView textViewStartingWaist;
    private TextView textViewStartingHip;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measurements,container,false);

        TextView textViewMeasure = view.findViewById(R.id.measurements_button_new_entry);

        inputNeck = view.findViewById(R.id.measurements_current_neck);
        inputHip = view.findViewById(R.id.measurements_current_hip);
        inputWaist = view.findViewById(R.id.measurements_current_waist);
        inputHeight = view.findViewById(R.id.measurements_current_height);

        textViewStartingHip = view.findViewById(R.id.measurements_starting_hip);
        textViewStartingNeck = view.findViewById(R.id.measurements_starting_neck);
        textViewStartingWaist = view.findViewById(R.id.measurements_starting_waist);

        textViewMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNewMeasurements(v);
            }
        });

        DisplayStartingMeasurements();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        DisplayPreviousEntries(view);
    }

    private void DisplayPreviousEntries(View view) {
        recyclerView = view.findViewById(R.id.measurements_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new MeasurementsRecyclerAdapter(view.getContext(),DBManager.getInstance().getMeasurementData());

        recyclerView.setAdapter(adapter);
    }

    private void DisplayStartingMeasurements() {

        textViewStartingWaist.setText(DBManager.getInstance().getData(Constants.DATA_TYPE_STARTING_WAIST));
        textViewStartingNeck.setText(DBManager.getInstance().getData(Constants.DATA_TYPE_STARTING_NECK));
        textViewStartingHip.setText(DBManager.getInstance().getData(Constants.DATA_TYPE_STARTING_HIPS));
    }

    private void enterNewMeasurements(View v) {


        if(!inputHip.getText().toString().isEmpty())
        {
            float hipEntryValue = Float.parseFloat(inputHip.getText().toString());
            addNewEntry(hipEntryValue, Constants.DATA_TYPE_HIPS);
        }

        if(!inputNeck.getText().toString().isEmpty())
        {
            float neckEntryValue = Float.parseFloat(inputNeck.getText().toString());
            addNewEntry(neckEntryValue, Constants.DATA_TYPE_NECK);
        }

        if(!inputWaist.getText().toString().isEmpty())
        {
            float waistEntryValue = Float.parseFloat(inputWaist.getText().toString());
            addNewEntry(waistEntryValue, Constants.DATA_TYPE_WAIST);
        }

        if(!inputHeight.getText().toString().isEmpty())
        {
            float heightEntryValue = Float.parseFloat(inputHeight.getText().toString());
            addNewEntry(heightEntryValue, Constants.DATA_TYPE_HEIGHT);
        }

        inputHip.getText().clear();
        inputWaist.getText().clear();
        inputNeck.getText().clear();
        inputHeight.getText().clear();

        Toast.makeText(v.getContext(),R.string.measurements_toast_entry,Toast.LENGTH_SHORT);
    }

    private void addNewEntry(float value, int dataType)
    {
        DBManager.getInstance().InsertData(dataType,value);
    }
}
