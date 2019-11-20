package hr.foi.a2fit4u.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.a2fit4u.GetData;
import hr.foi.a2fit4u.R;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edittext_username)
    EditText mTextUsername;
    @BindView(R.id.edittext_email)
    EditText mTextEmail;
    @BindView(R.id.edittext_password)
    EditText mTextPassword;
    @BindView(R.id.edittext_cnf_password)
    EditText mTextCnfPassword;

    @OnClick(R.id.button_register)
    void register(){
        //TODO implement register
        GetData getData = new GetData();
        Log.d("RegisterActivity", "Registracija u tijeku");

        //if(mTextPassword.equals(mTextCnfPassword)){
            Log.d("RegisterActivity","Lozinke su jednake");
            getData.registerUser(mTextUsername.getText().toString(),mTextEmail.getText().toString(),mTextPassword.getText().toString());
            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        //}


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }
}
