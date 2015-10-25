package in.wasure.wasurenew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    private EditText usr,pwd,fName,lName,phone;
    Button submit;
    ProgressDialog pd;

    String stUname,stPwd,stEmail,stFName,stLName,stPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        usr=(EditText) findViewById(R.id.activity_signup_email);
        pwd=(EditText) findViewById(R.id.activity_signup_password);
        fName=(EditText) findViewById(R.id.activity_signup_firstName);
        lName=(EditText) findViewById(R.id.activity_signup_lastName);
        phone=(EditText) findViewById(R.id.activity_signup_phone);
        submit=(Button) findViewById(R.id.activity_signup_signupButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(SignUpActivity.this);
                pd.setMessage("Signing Up");
                pd.setCancelable(false);
                pd.show();
                stEmail=stUname=usr.getText().toString();
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
                            Intent in = new Intent(SignUpActivity.this, MainActivity.class);
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
        });

    }
}
