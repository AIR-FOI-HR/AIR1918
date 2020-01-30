package hr.foi.a2fit4u.weight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.a2fit4u.GetData;
import hr.foi.a2fit4u.R;

public class WeightActivity extends AppCompatActivity {

    //@BindView(R.id.textview_weight)
    //TextView mTextViewCurrentWeight;

    //@OnClick(R.id.button_refresh_weight)
    void refreshWeight()
    {
        GetData getData = new GetData();
        //String currentWeight = "" + getData.getWeight(Integer.parseInt(getIntent().getStringExtra("USER_ID")));
        String currentWeight = "" + getData.getWeight(1);
        //mTextViewCurrentWeight.setText(currentWeight);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        ButterKnife.bind(this);
        refreshWeight();
    }
}
