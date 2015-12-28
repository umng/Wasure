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

//    String itemName="", wash="", iron="", wash_iron="", dryclean="";

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
//        wb.getSettings().setLoadWithOverviewMode(true);
//        wb.getSettings().setUseWideViewPort(true);
//        wb.getSettings().setBuiltInZoomControls(true);
//        wb.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl("http://wasure.azurewebsites.net/RateCard/ratecard.html");

//        pd = new ProgressDialog(RateCard.this);
//        pd.setMessage("Processing");
//        pd.setCancelable(false);
//        pd.show();
//
//        mainframe = (LinearLayout) findViewById(R.id.activity_ratecard_mainframe);
//
//        ParseQuery orderQuery = new ParseQuery("Price");
//        orderQuery.orderByDescending("createdAt");
//        orderQuery.findInBackground(new FindCallback<ParseObject>() {
//
//            @Override
//            public void done(List<ParseObject> content, ParseException pEx) {
//                // TODO Auto-generated method stub
//                if (pEx == null && content != null) {
//                    if (!(content.isEmpty())) {
//                        for (ParseObject itemObject : content) {
//
//                            if(itemObject.getString("itemName") != null)
//                            {
//                                itemName = itemObject.getString("itemName").trim();
//                            }
//
//                            if(itemObject.get("wash") != null)
//                            {
//                                wash = itemObject.get("wash").toString().trim();
//                            }
//
//                            if(itemObject.get("iron") != null)
//                            {
//                                iron = itemObject.get("iron").toString().trim();
//                            }
//
//                            if(itemObject.get("wash_iron") != null)
//                            {
//                                wash_iron = itemObject.get("wash_iron").toString().trim();
//                            }
//
//                            if(itemObject.get("dryclean") != null)
//                            {
//                                dryclean = itemObject.get("dryclean").toString().trim();
//                            }
//
//                            LinearLayout linearLayout_77 = new LinearLayout(RateCard.this);
//                            linearLayout_77.setOrientation(LinearLayout.VERTICAL);
////                            linearLayout_77.setPadding(int left, int top, (5/getApplicationContext().getResources().getDisplayMetrics().density), int bottom);
//                            LayoutParams layout_567 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//                            layout_567.width = LayoutParams.MATCH_PARENT;
//                            layout_567.height = LayoutParams.WRAP_CONTENT;
//                            linearLayout_77.setLayoutParams(layout_567);
//
//                            LinearLayout row_pant = new LinearLayout(RateCard.this);
//                            row_pant.setOrientation(LinearLayout.HORIZONTAL);
//                            row_pant.setId(R.id.row_pant);
//                            LayoutParams layout_855 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//                            layout_855.width = LayoutParams.MATCH_PARENT;
//                            layout_855.height = LayoutParams.WRAP_CONTENT;
//                            layout_855.topMargin = 10;
//                            row_pant.setLayoutParams(layout_855);
//
//                            LinearLayout linearLayout_546 = new LinearLayout(RateCard.this);
//                            linearLayout_546.setOrientation(LinearLayout.HORIZONTAL);
//                            LayoutParams layout_346 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//                            layout_346.width = LayoutParams.MATCH_PARENT;
//                            layout_346.height = LayoutParams.WRAP_CONTENT;
//                            layout_346.weight = 1.5f;
//                            linearLayout_546.setLayoutParams(layout_346);
//
//                            ImageView imageView_652 = new ImageView(RateCard.this);
//                            imageView_652.setImageResource(R.drawable.pant);
//                            LayoutParams layout_258 = new LayoutParams(32,32);
//                            layout_258.width = 32;
//                            layout_258.height = 32;
////                            layout_258.gravity = LayoutParams.CENTER;
//                            imageView_652.setLayoutParams(layout_258);
//                            linearLayout_546.addView(imageView_652);
//
//                            TextView textView_170 = new TextView(RateCard.this);
//                            textView_170.setText(itemName);
//                            textView_170.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//                            LayoutParams layout_507 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//                            layout_507.width = LayoutParams.WRAP_CONTENT;
//                            layout_507.height = LayoutParams.WRAP_CONTENT;
////                            layout_507.gravity = LayoutParams.CENTER;
//                            layout_507.leftMargin = 10;
//                            textView_170.setLayoutParams(layout_507);
//                            linearLayout_546.addView(textView_170);
//                            row_pant.addView(linearLayout_546);
//
//                            LinearLayout linearLayout_418 = new LinearLayout(RateCard.this);
//                            linearLayout_418.setOrientation(LinearLayout.HORIZONTAL);
//                            LayoutParams layout_627 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//                            layout_627.width = LayoutParams.MATCH_PARENT;
//                            layout_627.height = LayoutParams.WRAP_CONTENT;
//                            layout_627.weight = 1;
//                            linearLayout_418.setLayoutParams(layout_627);
//
//                            TextView textView_675 = new TextView(RateCard.this);
//                            textView_675.setText(wash);
//                            textView_675.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//                            LayoutParams layout_907 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//                            layout_907.width = LayoutParams.MATCH_PARENT;
//                            layout_907.weight = 1;
////                            layout_907.gravity = LayoutParams.CENTER_HORIZONTAL;
//                            textView_675.setLayoutParams(layout_907);
//                            linearLayout_418.addView(textView_675);
//
//                            TextView textView_555 = new TextView(RateCard.this);
//                            textView_555.setText(iron);
//                            textView_555.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//                            LayoutParams layout_249 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//                            layout_249.width = LayoutParams.MATCH_PARENT;
//                            layout_249.weight = 1;
////                            layout_249.gravity = LayoutParams.CENTER_HORIZONTAL;
//                            textView_555.setLayoutParams(layout_249);
//                            linearLayout_418.addView(textView_555);
//
//                            TextView textView_451 = new TextView(RateCard.this);
//                            textView_451.setText(wash_iron);
//                            textView_451.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//                            LayoutParams layout_851 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//                            layout_851.width = LayoutParams.MATCH_PARENT;
//                            layout_851.weight = 1;
////                            layout_851.gravity = LayoutParams.CENTER_HORIZONTAL;
//                            textView_451.setLayoutParams(layout_851);
//                            linearLayout_418.addView(textView_451);
//
//                            TextView textView_135 = new TextView(RateCard.this);
//                            textView_135.setText(dryclean);
//                            textView_135.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//                            LayoutParams layout_125 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//                            layout_125.width = LayoutParams.MATCH_PARENT;
//                            layout_125.weight = 1;
////                            layout_125.gravity = LayoutParams.CENTER_HORIZONTAL;
//                            textView_135.setLayoutParams(layout_125);
//                            linearLayout_418.addView(textView_135);
//                            row_pant.addView(linearLayout_418);
//                            linearLayout_77.addView(row_pant);
//
//                            mainframe.addView(linearLayout_77);
//
//                        }
//                        pd.dismiss();
//                    }
//                }
//            }
//        });

    }
}
