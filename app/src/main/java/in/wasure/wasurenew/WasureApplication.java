package in.wasure.wasurenew;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by Umang on 10/15/2015.
 */
public class WasureApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "f4J4dIaOMNEtBxnyPKAEAn0Gz4UbtddPN9VXkyRS", "8nFAfo2LPlvO1KFnQw3au1zTcDxdrz61txmGGCRr");

        ParseInstallation.getCurrentInstallation().saveInBackground();

//        ParsePush.subscribeInBackground("[channel name]", new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
//                } else {
//                    Log.e("com.parse.push", "failed to subscribe for push", e);
//                }
//            }
//        });
    }
}
