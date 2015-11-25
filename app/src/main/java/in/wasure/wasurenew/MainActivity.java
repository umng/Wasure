package in.wasure.wasurenew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class MainActivity extends Activity {

    Button logout,about,support,freshOrder,myorders;
    TextView header;
    ProgressDialog pd;

    private String stHeader ="";

    ParseUser user = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //logout
        logout=(Button)findViewById(R.id.activity_main_logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Logging Out");
                pd.setCancelable(false);
                pd.show();
                ParseUser.logOut();
                pd.dismiss();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        //about
        about = (Button) findViewById(R.id.activity_main_aboutButton);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(in);
            }
        });

        //support
        support = (Button) findViewById(R.id.activity_main_supportButton);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,SupportActivity.class);
                startActivity(in);
            }
        });

        //freshorder
        freshOrder = (Button) findViewById(R.id.activity_main_freshOrderButton);
        freshOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,FreshOrderActivity.class);
                startActivity(in);
            }
        });

        myorders = (Button) findViewById(R.id.activity_main_myOrdersButton);
        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyOrdersActivity.class));
            }
        });

        //fetch stHeader info
        header = (TextView) findViewById(R.id.activity_main_header);
        if(user.getBoolean("emailVerified")!=true)
        {
            header.setText("Sorry, " + user.getString("firstName").toString() + ".\nPlease verify your account first.");
        }
        else
        {
            stHeader += "Hey " + user.getString("firstName").toString() + ",\n";
            stHeader += "User name : " + user.getUsername().toString() + "\n";
            stHeader += "Email : " + user.getEmail().toString() + "\n";
            stHeader += "Phone : " + user.getString("phone").toString() + "\n";
            header.setText(stHeader);
        }
    }
}
