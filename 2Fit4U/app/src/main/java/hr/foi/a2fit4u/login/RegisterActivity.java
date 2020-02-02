package hr.foi.a2fit4u.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.core.managers.AccountManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.a2fit4u.GetData;
import hr.foi.a2fit4u.R;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_field_username)
    EditText mTextUsername;
    @BindView(R.id.register_field_email)
    EditText mTextEmail;
    @BindView(R.id.register_field_password)
    EditText mTextPassword;

    @OnClick(R.id.register_button_signup)
    void register(){

        Log.d("RegisterActivity", "Registracija u tijeku");

        AccountManager.getInstance().registerUser(mTextUsername.getText().toString(),mTextEmail.getText().toString(),mTextPassword.getText().toString());
        signIn();

    }
    @OnClick(R.id.register_button_signin)
    void signIn(){
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }


}
