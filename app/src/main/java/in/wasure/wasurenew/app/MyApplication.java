package in.wasure.wasurenew.app;

import android.app.Application;

import in.wasure.wasurenew.helper.ParseUtils;

/**
 * Created by Umang on 2/13/2016.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // register with parse
        ParseUtils.registerParse(this);
    }


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
