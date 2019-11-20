package hr.foi.a2fit4u.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.a2fit4u.R;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edittext_username)
    EditText mTextUsername;
    @BindView(R.id.edittext_password)
    EditText mTextPassword;
    @BindView(R.id.edittext_cnf_password)
    EditText mTextCnfPassword;

    @OnClick(R.id.button_register)
    void register(){
        //TODO implement register
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }
}
