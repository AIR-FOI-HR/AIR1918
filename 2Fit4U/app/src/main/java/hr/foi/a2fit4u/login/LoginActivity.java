package hr.foi.a2fit4u.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.a2fit4u.GetData;
import hr.foi.a2fit4u.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.edittext_username)
    EditText mTextUsername;
    @BindView(R.id.edittext_password)
    EditText mTextPassword;

    @OnClick(R.id.button_login)
    void login(){
        // TODO implement login

        GetData getData = new GetData();
        Log.d(TAG, "login: " + getData.getData());
        Map<String,String> mapa = getData.getData();
        for (Map.Entry<String,String> entry : mapa.entrySet())
        {
            mTextUsername.setText(entry.getValue());
            mTextPassword.setText(entry.getKey());
            Log.d("BAZAA", entry.getValue() );
        }
    }

    @OnClick(R.id.textview_register)
    void openRegisterForm(){
        Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }
}