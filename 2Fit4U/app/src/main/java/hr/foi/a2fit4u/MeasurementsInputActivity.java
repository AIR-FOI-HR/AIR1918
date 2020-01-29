package hr.foi.a2fit4u;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MeasurementsInputActivity extends AppCompatActivity {

   /* final EditText vrat = (EditText) findViewById(R.id.newNeck);
    String vratString = (String) vrat.getText().toString();
    float vratFloat = Float.parseFloat(vratString);*/

    @OnClick(R.id.btnSpremi)
    void send(){

        SendData sendData = new SendData();
        final EditText vrat = (EditText) findViewById(R.id.newNeck);
        String vratString = (String) vrat.getText().toString();
        float vratFloat = Float.parseFloat(vratString);

        final EditText struk = (EditText) findViewById(R.id.newWaist);
        String strukString = (String) struk.getText().toString();
        float strukFloat = Float.parseFloat(strukString);

        final EditText bokovi = (EditText) findViewById(R.id.newHips);
        String bokoviString = (String) bokovi.getText().toString();
        float bokoviFloat = Float.parseFloat(bokoviString);

        System.out.println(vratFloat);

        sendData.insertNewMeasurements(1, 2, vratFloat);
        sendData.insertNewMeasurements(1, 2, strukFloat);
        sendData.insertNewMeasurements(1, 2, bokoviFloat);

        vrat.setText("");
        struk.setText("");
        bokovi.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_measurements);

        ButterKnife.bind(this);
    }
}
