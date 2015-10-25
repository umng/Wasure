package in.wasure.wasurenew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;

/**
 * Created by Umang on 10/23/2015.
 */
public class DispatchActivity extends Activity {
    public DispatchActivity()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ParseUser.getCurrentUser()!=null)
        {
            startActivity(new Intent(DispatchActivity.this,MainActivity.class));
        }
        else
        {
            //change it to WelcomeActivity
            startActivity(new Intent(DispatchActivity.this,LoginActivity.class));
        }
    }
}
