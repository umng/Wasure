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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Umang on 10/15/2015.
 */
public class SignUpActivity extends Activity {

    private TextInputLayout inputLayoutFirstName, inputLayoutLastName, inputLayoutEmail, inputLayoutPassword;
    private EditText inputEmail,pwd,fName,lName,phone;
    Button submit;
    ProgressDialog pd;

    String stUname,stPwd,stEmail,stFName,stLName,stPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        inputEmail=(EditText) findViewById(R.id.activity_signup_email);
        pwd=(EditText) findViewById(R.id.activity_signup_password);
        fName=(EditText) findViewById(R.id.activity_signup_firstName);
        lName=(EditText) findViewById(R.id.activity_signup_lastName);
        phone=(EditText) findViewById(R.id.activity_signup_phone);
        submit=(Button) findViewById(R.id.activity_signup_signupButton);
        inputLayoutFirstName= (TextInputLayout) findViewById(R.id.input_layout_firstname_signup);
        inputLayoutLastName= (TextInputLayout) findViewById(R.id.input_layout_lastname_signup);
        inputLayoutEmail= (TextInputLayout) findViewById(R.id.input_layout_email_signup);
        inputLayoutPassword= (TextInputLayout) findViewById(R.id.input_layout_password_signup);

        fName.addTextChangedListener(new MyTextWatcher(fName));
        lName.addTextChangedListener(new MyTextWatcher(lName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        pwd.addTextChangedListener(new MyTextWatcher(pwd));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(SignUpActivity.this);
                pd.setMessage("Signing Up");
                pd.setCancelable(false);
                pd.show();

                if(submitForm() == 1)
                {
                    stEmail=stUname=inputEmail.getText().toString();
                    stFName=fName.getText().toString();
                    stLName=lName.getText().toString();
                    stPhone=phone.getText().toString();
                    stPwd = pwd.getText().toString();
                    ParseUser user=new ParseUser();
                    user.setUsername(stUname);
                    user.setPassword(stPwd);
                    user.setEmail(stEmail);
                    user.put("firstName", stFName);
                    user.put("lastName",stLName);
                    user.put("phone",stPhone);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                pd.dismiss();
                                Toast.makeText(SignUpActivity.this, "You have SignedUp!", Toast.LENGTH_LONG).show();
                                Intent in = new Intent(SignUpActivity.this, AddressActivity.class);
                                in.addFlags(in.FLAG_ACTIVITY_NEW_TASK);
                                in.addFlags(in.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(in);
                            } else {
                                pd.dismiss();
                                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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

        if (!validateFirstName()) {
            return 0;
        }

        if (!validateLastName()) {
            return 0;
        }

        //Toast.makeText(getApplicationContext(), "Thank You!" , Toast.LENGTH_SHORT).show();
        return 1;
    }

    private boolean validateFirstName() {
        if (fName.getText().toString().trim().isEmpty()) {
            inputLayoutFirstName.setError(getString(R.string.err_msg_FirstName));
            requestFocus(fName);
            return false;
        } else {
            inputLayoutFirstName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLastName() {
        if (lName.getText().toString().trim().isEmpty()) {
            inputLayoutLastName.setError(getString(R.string.err_msg_LastName));
            requestFocus(lName);
            return false;
        } else {
            inputLayoutLastName.setErrorEnabled(false);
        }

        return true;
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
                case R.id.activity_signup_firstName:
                    validateFirstName();
                    break;
                case R.id.activity_signup_lastName:
                    validateLastName();
                    break;
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
