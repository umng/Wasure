package in.wasure.wasurenew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Umang on 10/16/2015.
 */
public class LoginActivity extends Activity {

    private EditText usr,pwd;
    Button signup,login;
    ProgressDialog pd;

    String stUname,stPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usr=(EditText) findViewById(R.id.activity_login_username);
        pwd=(EditText) findViewById(R.id.activity_login_password);
        signup = (Button) findViewById(R.id.activity_login_gotoSignUpButton);
        login = (Button)findViewById(R.id.activity_login_loginButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(in);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(LoginActivity.this);
                pd.setMessage("Logging In");
                pd.setCancelable(false);
                pd.show();
                stUname=usr.getText().toString();
                stPwd=pwd.getText().toString();
                ParseUser.logInInBackground(stUname, stPwd, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if(e==null)
                        {
                            pd.dismiss();
                            Toast.makeText(LoginActivity.this,"Successfully Logged In!",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else
                        {
                            pd.dismiss();
                            Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
