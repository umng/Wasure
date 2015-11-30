package in.wasure.wasurenew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
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
import com.parse.RequestPasswordResetCallback;

import in.wasure.wasurenew.activity.MainActivity;

/**
 * Created by Umang on 11/29/2015.
 */
public class ResetPasswordActivity extends Activity {

    private TextInputLayout inputLayoutEmail;
    private EditText inputEmail;
    Button reset;
    ProgressDialog pd;

    String stUname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail=(EditText) findViewById(R.id.activity_reset_username);
        reset = (Button)findViewById(R.id.activity_reset_resetButton);
        inputLayoutEmail= (TextInputLayout) findViewById(R.id.input_layout_email_reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(ResetPasswordActivity.this);
                pd.setMessage("Processing");
                pd.setCancelable(false);
                pd.show();

                if(submitForm() == 1)
                {
                    stUname=inputEmail.getText().toString();
                    ParseUser.requestPasswordResetInBackground(stUname, new RequestPasswordResetCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                pd.dismiss();
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(ResetPasswordActivity.this);
                                builder1.setMessage("Check your email and set up your new password.");
                                builder1.setCancelable(true);
                                builder1.setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                                finish();
                                            }
                                        });
                                final AlertDialog alert11 = builder1.create();
                                alert11.show();
                            } else {
                                pd.dismiss();
                                Toast.makeText(ResetPasswordActivity.this, "Invalid Email or Network Problem", Toast.LENGTH_LONG).show();
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
            }
        }
    }

}
