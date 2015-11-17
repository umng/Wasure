package in.wasure.wasurenew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.*;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Umang on 10/11/2015.
 */
public class FreshOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshorder);

        LinearLayout parentlayout = (LinearLayout) findViewById(R.id.activity_freshorder_mainframe);

        String itemList[]={"Pant", "Shirt", "T-Shirt", "Jeans", "Towel"};

//        for(int i=0;i<5;i++)
//        {
//            LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f);
//            lparams.setMargins(0,0,0,20);
//
//            LinearLayout layout = new LinearLayout(this);
//            layout.setOrientation(LinearLayout.HORIZONTAL);
//            layout.setLayoutParams(lparams);
//
//            TextView itemName = new TextView(this);
//            itemName.setLayoutParams(lparams);
//            itemName.setText(itemList[i]);
//            layout.addView(itemName);
//
//            LayoutParams lparams2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 2.0f);
//
//            Spinner spinner = new Spinner(this);
//            itemName.setLayoutParams(lparams2);
//            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.nOfItems)); //selected item will look like a spinner set from XML
//            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(spinnerArrayAdapter);
//            layout.addView(spinner);
//
//            Spinner spinner2 = new Spinner(this);
//            itemName.setLayoutParams(lparams);
//            ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.srvType)); //selected item will look like a spinner set from XML
//            spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner2.setAdapter(spinnerArrayAdapter2);
//            layout.addView(spinner2);
//
//            parentlayout.addView(layout);
//        }


    }
}
