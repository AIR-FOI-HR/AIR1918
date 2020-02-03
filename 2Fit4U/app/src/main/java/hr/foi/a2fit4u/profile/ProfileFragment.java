package hr.foi.a2fit4u.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.core.managers.DBManager;
import com.example.core.util.Constants;

import hr.foi.a2fit4u.R;
import hr.foi.a2fit4u.measurements.MeasurementsFragment;
import hr.foi.a2fit4u.weight.WeightFragment;

public class ProfileFragment extends Fragment {

    private TextView textViewCurrentWeight;
    private TextView textViewGoalWeight;
    private TextView textViewHips;
    private TextView textViewNeck;
    private TextView textViewWaist;
    private TextView textViewBodyFat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        textViewBodyFat = view.findViewById(R.id.profile_bodyfat);
        textViewCurrentWeight = view.findViewById(R.id.profile_current_weight);
        textViewGoalWeight = view.findViewById(R.id.profile_goal_weight);
        textViewHips = view.findViewById(R.id.profile_hips);
        textViewNeck = view.findViewById(R.id.profile_neck);
        textViewWaist = view.findViewById(R.id.profile_waist);

        TextView buttonWeight = view.findViewById(R.id.profile_weighin);
        TextView buttonMeasurements = view.findViewById(R.id.profile_new_measurements);

        buttonWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWeightEntry();
            }
        });

        buttonMeasurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newMeasurementsEntry();
            }
        });

        DisplayBodyFat();
        DisplayCurrentMeasurements();
        DisplayCurrentWeight();
        DisplayGoalWeight();

        return view;
    }

    private void newMeasurementsEntry() {
        this.getFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, new MeasurementsFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("")
                .commit();
    }

    private void newWeightEntry() {
        this.getFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, new WeightFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("")
                .commit();
    }

    private void DisplayCurrentWeight()
    {
        textViewCurrentWeight.setText(DBManager.getInstance().getData(Constants.DATA_TYPE_WEIGHT) + " KG");
    }

    private void DisplayGoalWeight()
    {

    }
    private void DisplayCurrentMeasurements()
    {
        textViewWaist.setText(DBManager.getInstance().getData(Constants.DATA_TYPE_WAIST));
        textViewNeck.setText(DBManager.getInstance().getData(Constants.DATA_TYPE_NECK));
        textViewHips.setText(DBManager.getInstance().getData(Constants.DATA_TYPE_HIPS));
    }

    private void DisplayBodyFat()
    {

    }
}
