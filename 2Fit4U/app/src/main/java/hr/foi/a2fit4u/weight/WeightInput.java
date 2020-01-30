package hr.foi.a2fit4u.weight;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.a2fit4u.R;
import hr.foi.a2fit4u.SendData;

public class WeightInput extends AppCompatActivity {

    @OnClick(R.id.btnSpremiTezinu)
    void insertWeight(){
        final EditText tezina = (EditText) findViewById(R.id.newTezina);
        String tezinaString = (String) tezina.getText().toString();
        float tezinaFloat = Float.parseFloat(tezinaString);

        SendData sendData = new SendData();

        sendData.insertNewMeasurements(1, 1, tezinaFloat);

        //tezina.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_in);

        ButterKnife.bind(this);
    }
}
