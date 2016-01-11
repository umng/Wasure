package in.wasure.wasurenew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import in.wasure.wasurenew.activity.MainActivity;

/**
 * Created by Umang on 10/16/2015.
 */
public class LoginActivity extends Activity {

    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private EditText inputEmail,pwd;
    Button signup,login, reset;
    ProgressDialog pd;

    String stUname,stPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail=(EditText) findViewById(R.id.activity_login_username);
        pwd=(EditText) findViewById(R.id.activity_login_password);
        signup = (Button) findViewById(R.id.activity_login_gotoSignUpButton);
        reset = (Button) findViewById(R.id.activity_login_gotoResetButton);
        login = (Button)findViewById(R.id.activity_login_loginButton);
        inputLayoutEmail= (TextInputLayout) findViewById(R.id.input_layout_email_login);
        inputLayoutPassword= (TextInputLayout) findViewById(R.id.input_layout_password_login);

        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        pwd.addTextChangedListener(new MyTextWatcher(pwd));

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(in);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(LoginActivity.this);
                pd.setMessage("Logging In");
                pd.setCancelable(false);
                pd.show();

                if(submitForm() == 1)
                {
                    stUname=inputEmail.getText().toString();
                    stPwd=pwd.getText().toString();
                    ParseUser.logInInBackground(stUname, stPwd, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if(e==null)
                            {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this,"Successfully Logged In!",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                            else
                            {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this, "Invalid Email or Password" ,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else
                {
                    pd.dismiss();
                }
            }
        });
    }

    /**
     * Validating form
     */
    private int submitForm() {
        if (!validateEmail()) {
            return 0;
        }

        if (!validatePassword()) {
            return 0;
        }

        //Toast.makeText(getApplicationContext(), "Thank You!" , Toast.LENGTH_SHORT).show();
        return 1;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (pwd.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(pwd);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.activity_signup_email:
                    validateEmail();
                    break;
                case R.id.activity_signup_password:
                    validatePassword();
                    break;
            }
        }
    }
}
