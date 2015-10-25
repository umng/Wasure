package in.wasure.wasurenew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Umang on 10/25/2015.
 */
public class AddressActivity extends Activity {

    EditText room;
    Button continu,skip;
    Spinner hostel,block;
    String selectedHostel,selectedBlock,error;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        skip = (Button) findViewById(R.id.activity_address_skip);
        continu = (Button) findViewById(R.id.activity_address_continue);
        room = (EditText) findViewById(R.id.activity_address_room);
        hostel = (Spinner) findViewById(R.id.activity_address_hostel);
        block = (Spinner) findViewById(R.id.activity_address_block);

        pd = new ProgressDialog(AddressActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);

        //skip
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                ParseObject details = new ParseObject("UserDetails");
                details.put("hostel","");
                details.put("block","");
                details.put("room","");
                details.put("username", ParseUser.getCurrentUser());
                details.saveInBackground();
                pd.dismiss();
                startActivity(new Intent(AddressActivity.this,MainActivity.class));
            }
        });

        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                //validate room number
                int x =Integer.parseInt(room.getText().toString());
                if(x>0 && x<1050)
                {
                    ParseUser user = ParseUser.getCurrentUser();
                    ParseObject details = new ParseObject("UserDetails");
                    details.put("hostel",hostel.getSelectedItem().toString());
                    details.put("block",block.getSelectedItem().toString());
                    details.put("room",room.getText().toString());
                    details.put("username", ParseUser.getCurrentUser());
                    details.saveInBackground();
                    pd.dismiss();
                    startActivity(new Intent(AddressActivity.this,MainActivity.class));
                }
                else
                {
                    error = "room";
                    pd.dismiss();
                }
            }
        });
    }
}
