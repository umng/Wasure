package in.wasure.wasurenew;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.*;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class RateCard extends AppCompatActivity {

    ProgressDialog pd;
    Toolbar mToolbar;
    LinearLayout mainframe;
    WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_card);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_freshorder);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Rate Card");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        wb=(WebView)findViewById(R.id.webView1);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.loadUrl("http://wasure.azurewebsites.net/RateCard/ratecard.html");

    }
}
