package hr.foi.a2fit4u.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.core.managers.AccountManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.a2fit4u.MainActivity;
import hr.foi.a2fit4u.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.login_field_username)
    EditText mTextUsername;
    @BindView(R.id.login_field_password)
    EditText mTextPassword;

    @OnClick(R.id.login_button_signin)
    void login(){
        if(AccountManager.getInstance().checkUser(mTextUsername.getText().toString(),mTextPassword.getText().toString()))
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, getString(R.string.login_invalid_credentials), Toast.LENGTH_SHORT).show();
        }


    }

    @OnClick(R.id.login_button_register)
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