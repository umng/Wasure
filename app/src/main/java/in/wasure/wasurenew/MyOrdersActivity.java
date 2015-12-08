package in.wasure.wasurenew;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Umang on 11/24/2015.
 */
public class MyOrdersActivity extends AppCompatActivity {

    ProgressDialog pd;
    Toolbar mToolbar;

    ParseUser user = ParseUser.getCurrentUser();
    ParseObject orderId;

    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_freshorder);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("My Orders");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pd = new ProgressDialog(MyOrdersActivity.this);
        pd.setMessage("Processing");
        pd.setCancelable(false);
        pd.show();

        ParseQuery orderQuery = new ParseQuery("Orders");
        orderQuery.whereEqualTo("username", user);
        orderQuery.orderByDescending("createdAt");
        orderQuery.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> content, ParseException pEx) {
                // TODO Auto-generated method stub
                if (pEx == null && content != null) {
                    if (!(content.isEmpty())) {
                        for (ParseObject orderObject : content) {
                            orderId = orderObject;

                            LinearLayout linearLayout_54 = (LinearLayout) findViewById(R.id.activity_myorders_listlayout);

                            LinearLayout hello = new LinearLayout(MyOrdersActivity.this);
                            hello.setOrientation(LinearLayout.HORIZONTAL);
                            hello.setBackgroundColor(Color.parseColor("#1A237E"));
                            hello.setId(R.id.hello);
                            LayoutParams layout_978 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_978.width = LayoutParams.MATCH_PARENT;
                            layout_978.height = LayoutParams.WRAP_CONTENT;
                            layout_978.topMargin = 20;
                            hello.setLayoutParams(layout_978);

                            LinearLayout linearLayout_118 = new LinearLayout(MyOrdersActivity.this);
                            linearLayout_118.setOrientation(LinearLayout.VERTICAL);
                            LayoutParams layout_94 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_94.width = LayoutParams.MATCH_PARENT;
                            layout_94.height = LayoutParams.WRAP_CONTENT;
                            layout_94.weight = 1;
                            linearLayout_118.setLayoutParams(layout_94);

                            TextView textView_305 = new TextView(MyOrdersActivity.this);
                            textView_305.setText("Order ID: " + orderObject.get("orderId").toString());
                            textView_305.setTextColor(Color.parseColor("#3F51B5"));
                            textView_305.setBackgroundColor(Color.parseColor("#8C9EFF"));
                            LayoutParams layout_34 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_34.width = LayoutParams.MATCH_PARENT;
                            layout_34.height = LayoutParams.WRAP_CONTENT;
                            layout_34.leftMargin = 10;
                            textView_305.setLayoutParams(layout_34);
                            linearLayout_118.addView(textView_305);

                            LinearLayout linearLayout_598 = new LinearLayout(MyOrdersActivity.this);
                            linearLayout_598.setOrientation(LinearLayout.HORIZONTAL);
                            LayoutParams layout_557 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_557.width = LayoutParams.MATCH_PARENT;
                            layout_557.height = LayoutParams.WRAP_CONTENT;
                            linearLayout_598.setLayoutParams(layout_557);

                            LinearLayout linearLayout_643 = new LinearLayout(MyOrdersActivity.this);
                            linearLayout_643.setOrientation(LinearLayout.VERTICAL);
                            LayoutParams layout_785 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_785.width = LayoutParams.MATCH_PARENT;
                            layout_785.height = LayoutParams.WRAP_CONTENT;
                            layout_785.weight = 2;
                            layout_785.topMargin = 5;
                            layout_785.leftMargin = 10;
                            linearLayout_643.setLayoutParams(layout_785);

                            TextView textView_196 = new TextView(MyOrdersActivity.this);
                            textView_196.setText("Item Name");
                            textView_196.setTextColor(Color.parseColor("#E8EAF6"));
                            LayoutParams layout_239 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_239.width = LayoutParams.MATCH_PARENT;
                            layout_239.height = LayoutParams.WRAP_CONTENT;
                            layout_239.bottomMargin = 5;
                            textView_196.setLayoutParams(layout_239);
                            linearLayout_643.addView(textView_196);
                            final LinearLayout linearLayout_644 = linearLayout_643;

                            linearLayout_598.addView(linearLayout_644);

                            LinearLayout linearLayout_599 = new LinearLayout(MyOrdersActivity.this);
                            linearLayout_599.setOrientation(LinearLayout.VERTICAL);
                            LayoutParams layout_881 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_881.width = LayoutParams.MATCH_PARENT;
                            layout_881.height = LayoutParams.WRAP_CONTENT;
                            layout_881.weight = 3;
                            layout_881.topMargin = 5;
                            linearLayout_599.setLayoutParams(layout_881);

                            TextView textView_60 = new TextView(MyOrdersActivity.this);
                            textView_60.setText("No.");
                            textView_60.setTextColor(Color.parseColor("#E8EAF6"));
                            LayoutParams layout_178 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_178.width = LayoutParams.MATCH_PARENT;
                            layout_178.height = LayoutParams.WRAP_CONTENT;
                            layout_178.bottomMargin = 5;
                            textView_60.setLayoutParams(layout_178);
                            linearLayout_599.addView(textView_60);
                            final LinearLayout linearLayout_600 = linearLayout_599;

                            linearLayout_598.addView(linearLayout_600);

                            LinearLayout linearLayout_508 = new LinearLayout(MyOrdersActivity.this);
                            linearLayout_508.setOrientation(LinearLayout.VERTICAL);
                            LayoutParams layout_957 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_957.width = LayoutParams.MATCH_PARENT;
                            layout_957.height = LayoutParams.WRAP_CONTENT;
                            layout_957.weight = 3;
                            layout_957.topMargin = 5;
                            linearLayout_508.setLayoutParams(layout_957);

                            TextView textView_116 = new TextView(MyOrdersActivity.this);
                            textView_116.setText("Price");
                            textView_116.setTextColor(Color.parseColor("#E8EAF6"));
                            LayoutParams layout_386 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_386.width = LayoutParams.MATCH_PARENT;
                            layout_386.height = LayoutParams.WRAP_CONTENT;
                            layout_386.bottomMargin = 5;
                            textView_116.setLayoutParams(layout_386);
                            linearLayout_508.addView(textView_116);
                            final LinearLayout linearLayout_509 = linearLayout_508;

                            linearLayout_598.addView(linearLayout_509);

                            //fetching items
                            ParseQuery itemQuery = new ParseQuery("ItemsOrdered");
                            itemQuery.whereEqualTo("orderId", orderId);
                            itemQuery.orderByDescending("createdAt");
                            itemQuery.findInBackground(new FindCallback<ParseObject>() {

                                @Override
                                public void done(List<ParseObject> content, ParseException pEx) {
                                    // TODO Auto-generated method stub
                                    if (pEx == null && content != null) {
                                        for (ParseObject itemObject : content) {

                                            if (!(itemObject.getString("itemName").equals(""))) {
                                                //set item name
                                                TextView textView_860 = new TextView(MyOrdersActivity.this);
                                                textView_860.setText(itemObject.getString("itemName").trim());
                                                textView_860.setTextColor(Color.parseColor("#C5CAE9"));
                                                LayoutParams layout_36 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.WRAP_CONTENT);
                                                layout_36.width = LayoutParams.MATCH_PARENT;
                                                layout_36.height = LayoutParams.WRAP_CONTENT;
                                                textView_860.setLayoutParams(layout_36);
                                                linearLayout_644.addView(textView_860);


                                                //set item number
                                                TextView textView_779 = new TextView(MyOrdersActivity.this);
                                                textView_779.setText(itemObject.get("numberItems") + "");
                                                textView_779.setTextColor(Color.parseColor("#C5CAE9"));
                                                LayoutParams layout_343 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.WRAP_CONTENT);
                                                layout_343.width = LayoutParams.MATCH_PARENT;
                                                layout_343.height = LayoutParams.WRAP_CONTENT;
                                                textView_779.setLayoutParams(layout_343);
                                                linearLayout_600.addView(textView_779);

                                                //set price
                                                TextView textView_935 = new TextView(MyOrdersActivity.this);
                                                textView_935.setText((Integer) itemObject.get("numberItems")
                                                        * (Integer) itemObject.get("rate") + "");
                                                textView_935.setTextColor(Color.parseColor("#C5CAE9"));
                                                LayoutParams layout_233 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.WRAP_CONTENT);
                                                layout_233.width = LayoutParams.MATCH_PARENT;
                                                layout_233.height = LayoutParams.WRAP_CONTENT;
                                                textView_935.setLayoutParams(layout_233);
                                                linearLayout_509.addView(textView_935);
                                            }
                                        }
                                    } else {
                                        Toast.makeText(MyOrdersActivity.this, "Error in fetching items try again!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                            linearLayout_118.addView(linearLayout_598);

                            TextView textView_198 = new TextView(MyOrdersActivity.this);
                            textView_198.setText(orderObject.getString("address"));
                            textView_198.setTextColor(Color.parseColor("#E8EAF6"));
                            LayoutParams layout_234 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_234.width = LayoutParams.MATCH_PARENT;
                            layout_234.height = LayoutParams.WRAP_CONTENT;
                            layout_234.bottomMargin = 5;
                            textView_198.setLayoutParams(layout_234);
                            linearLayout_118.addView(textView_198);

                            final LinearLayout linearLayout_119 = linearLayout_118;

                            ParseQuery totalQuery = new ParseQuery("ItemsOrdered");
                            totalQuery.whereEqualTo("orderId", orderId);
                            totalQuery.orderByDescending("createdAt");
                            totalQuery.findInBackground(new FindCallback<ParseObject>() {

                                @Override
                                public void done(List<ParseObject> content, ParseException pEx) {
                                    // TODO Auto-generated method stub
                                    if (pEx == null && content != null) {
                                        total = 0;
                                        for (ParseObject itemObject : content) {

                                            if (!(itemObject.getString("itemName").equals(""))) {
                                                //set total amount
                                                total += ((Integer) itemObject.get("numberItems") * (Integer) itemObject.get("rate"));
                                            }
                                        }

                                        TextView textView_796 = new TextView(MyOrdersActivity.this);
                                        textView_796.setText("Total: " + String.valueOf(total) + " Rs.");
                                        textView_796.setTextColor(Color.parseColor("#8C9EFF"));
                                        LayoutParams layout_909 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT);
                                        layout_909.width = LayoutParams.MATCH_PARENT;
                                        layout_909.height = LayoutParams.WRAP_CONTENT;
                                        layout_909.topMargin = 10;
                                        layout_909.leftMargin = 10;
                                        textView_796.setLayoutParams(layout_909);
                                        linearLayout_119.addView(textView_796);

                                    } else {
                                        Toast.makeText(MyOrdersActivity.this, "Error in fetching total amount try again!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                            hello.addView(linearLayout_118);

                            LinearLayout linearLayout_861 = new LinearLayout(MyOrdersActivity.this);
                            linearLayout_861.setOrientation(LinearLayout.VERTICAL);
                            LayoutParams layout_991 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_991.width = LayoutParams.MATCH_PARENT;
                            layout_991.height = LayoutParams.WRAP_CONTENT;
                            layout_991.weight = 3;
                            linearLayout_861.setLayoutParams(layout_991);

                            TextView textView_148 = new TextView(MyOrdersActivity.this);
                            //textView_148.setTextColor(Color.parseColor("#009688"));
                            textView_148.setText((orderObject.getString("status")).toUpperCase());
                            textView_148.setBackgroundColor(Color.parseColor("#009688"));
                            LayoutParams layout_721 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_721.width = LayoutParams.MATCH_PARENT;
                            layout_721.height = LayoutParams.WRAP_CONTENT;
                            textView_148.setLayoutParams(layout_721);
                            linearLayout_861.addView(textView_148);

                            LinearLayout linearLayout_26 = new LinearLayout(MyOrdersActivity.this);
                            linearLayout_26.setOrientation(LinearLayout.VERTICAL);
                            LayoutParams layout_849 = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_849.width = LayoutParams.WRAP_CONTENT;
                            layout_849.height = LayoutParams.WRAP_CONTENT;
                            linearLayout_26.setLayoutParams(layout_849);

                            TextView textView_846 = new TextView(MyOrdersActivity.this);
                            textView_846.setTextColor(Color.parseColor("#8C9EFF"));
                            Format dateFormatter = new SimpleDateFormat("dd");
                            textView_846.setText(dateFormatter.format(orderObject.getDate("dateOrdered")));
                            textView_846.setTextSize((80 / getApplicationContext().getResources().getDisplayMetrics().scaledDensity));
                            LayoutParams layout_555 = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_555.width = LayoutParams.WRAP_CONTENT;
                            layout_555.height = LayoutParams.WRAP_CONTENT;
                            layout_555.gravity = Gravity.CENTER;
                            textView_846.setLayoutParams(layout_555);
                            linearLayout_26.addView(textView_846);

                            TextView textView_799 = new TextView(MyOrdersActivity.this);
                            textView_799.setTextSize((30 / getApplicationContext().getResources().getDisplayMetrics().scaledDensity));
                            Format monthFormatter = new SimpleDateFormat("MMMM");
                            textView_799.setText(monthFormatter.format(orderObject.getDate("dateOrdered")));
                            textView_799.setTextColor(Color.parseColor("#536DFE"));
                            LayoutParams layout_444 = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout_444.width = LayoutParams.WRAP_CONTENT;
                            layout_444.height = LayoutParams.WRAP_CONTENT;
                            layout_444.topMargin = -5;
                            layout_444.gravity = Gravity.CENTER;
                            textView_799.setLayoutParams(layout_444);
                            linearLayout_26.addView(textView_799);
                            linearLayout_861.addView(linearLayout_26);
                            hello.addView(linearLayout_861);
                            linearLayout_54.addView(hello);




                        }
                    } else {
                        Toast.makeText(MyOrdersActivity.this, "Error in fetching orders try again!", Toast.LENGTH_LONG).show();
                    }
                    pd.dismiss();
                }
            }
        });
    }
}
