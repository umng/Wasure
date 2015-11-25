package in.wasure.wasurenew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.*;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Umang on 10/11/2015.
 */
public class FreshOrderActivity extends AppCompatActivity {

    private int x=1;
    private String[] nofItem={"","","","","","","","","","","","","","","","","","",""};
    private int[] itemSelected={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private String[] itemName={"","","","","","","","","","","","","","","","","","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshorder);

        final LinearLayout frame2 = (LinearLayout) findViewById(R.id.activity_freshorder_frame2);
        final LinearLayout frame3 = (LinearLayout) findViewById(R.id.activity_freshorder_frame3);
        final Button showmore = (Button) findViewById(R.id.activity_freshorder_showmore);
        final Button ordernow = (Button) findViewById(R.id.activity_freshorder_ordernow);

        final Spinner item1= (Spinner) findViewById(R.id.noItems1);
        final Spinner item2= (Spinner) findViewById(R.id.noItems2);
        final Spinner item3= (Spinner) findViewById(R.id.noItems3);
        final Spinner item4= (Spinner) findViewById(R.id.noItems4);
        final Spinner item5= (Spinner) findViewById(R.id.noItems5);
        final Spinner item6= (Spinner) findViewById(R.id.noItems6);
        final Spinner item7= (Spinner) findViewById(R.id.noItems7);
        final Spinner item8= (Spinner) findViewById(R.id.noItems8);
        final Spinner item9= (Spinner) findViewById(R.id.noItems9);
        final Spinner item10= (Spinner) findViewById(R.id.noItems10);
        final Spinner item11= (Spinner) findViewById(R.id.noItems11);
        final Spinner item12= (Spinner) findViewById(R.id.noItems12);
        final Spinner item13= (Spinner) findViewById(R.id.noItems13);
        final Spinner item14= (Spinner) findViewById(R.id.noItems14);
        final Spinner item15= (Spinner) findViewById(R.id.noItems15);
        final Spinner item16= (Spinner) findViewById(R.id.noItems16);
        final Spinner item17= (Spinner) findViewById(R.id.noItems17);
        final Spinner item18= (Spinner) findViewById(R.id.noItems18);
        final Spinner item19= (Spinner) findViewById(R.id.noItems19);

        final Spinner srvType1= (Spinner) findViewById(R.id.serviceType1);
        final Spinner srvType2= (Spinner) findViewById(R.id.serviceType2);
        final Spinner srvType3= (Spinner) findViewById(R.id.serviceType3);
        final Spinner srvType4= (Spinner) findViewById(R.id.serviceType4);
        final Spinner srvType5= (Spinner) findViewById(R.id.serviceType5);
        final Spinner srvType6= (Spinner) findViewById(R.id.serviceType6);
        final Spinner srvType7= (Spinner) findViewById(R.id.serviceType7);
        final Spinner srvType8= (Spinner) findViewById(R.id.serviceType8);
        final Spinner srvType9= (Spinner) findViewById(R.id.serviceType9);
        final Spinner srvType10= (Spinner) findViewById(R.id.serviceType10);
        final Spinner srvType11= (Spinner) findViewById(R.id.serviceType11);
        final Spinner srvType12= (Spinner) findViewById(R.id.serviceType12);
        final Spinner srvType13= (Spinner) findViewById(R.id.serviceType13);
        final Spinner srvType14= (Spinner) findViewById(R.id.serviceType14);
        final Spinner srvType15= (Spinner) findViewById(R.id.serviceType15);
        final Spinner srvType16= (Spinner) findViewById(R.id.serviceType16);
        final Spinner srvType17= (Spinner) findViewById(R.id.serviceType17);
        final Spinner srvType18= (Spinner) findViewById(R.id.serviceType18);
        final Spinner srvType19= (Spinner) findViewById(R.id.serviceType19);

        showmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x==1)
                {
                    frame2.setVisibility(View.VISIBLE);
                    x++;
                }
                else
                {
                    showmore.setVisibility(View.GONE);
                    frame3.setVisibility(View.VISIBLE);
                }
            }
        });

        ordernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                x=1;
                for(int j=0; j<19; j++)
                {
                    nofItem[j] = "";
                    itemSelected[j] = 0;
                    itemName[j] ="";
                }

                nofItem[0] = item1.getSelectedItem().toString();
                nofItem[1] = item2.getSelectedItem().toString();
                nofItem[2] = item3.getSelectedItem().toString();
                nofItem[3] = item4.getSelectedItem().toString();
                nofItem[4] = item5.getSelectedItem().toString();
                nofItem[5] = item6.getSelectedItem().toString();
                nofItem[6] = item7.getSelectedItem().toString();
                nofItem[7] = item8.getSelectedItem().toString();
                nofItem[8] = item9.getSelectedItem().toString();
                nofItem[9] = item10.getSelectedItem().toString();
                nofItem[10] = item11.getSelectedItem().toString();
                nofItem[11] = item12.getSelectedItem().toString();
                nofItem[12] = item13.getSelectedItem().toString();
                nofItem[13] = item14.getSelectedItem().toString();
                nofItem[14] = item15.getSelectedItem().toString();
                nofItem[15] = item16.getSelectedItem().toString();
                nofItem[16] = item17.getSelectedItem().toString();
                nofItem[17] = item18.getSelectedItem().toString();
                nofItem[18] = item19.getSelectedItem().toString();

                itemName[0] = srvType1.getSelectedItem().toString();
                itemName[1] = srvType2.getSelectedItem().toString();
                itemName[2] = srvType3.getSelectedItem().toString();
                itemName[3] = srvType4.getSelectedItem().toString();
                itemName[4] = srvType5.getSelectedItem().toString();
                itemName[5] = srvType6.getSelectedItem().toString();
                itemName[6] = srvType7.getSelectedItem().toString();
                itemName[7] = srvType8.getSelectedItem().toString();
                itemName[8] = srvType9.getSelectedItem().toString();
                itemName[9] = srvType10.getSelectedItem().toString();
                itemName[10] = srvType11.getSelectedItem().toString();
                itemName[11] = srvType12.getSelectedItem().toString();
                itemName[12] = srvType13.getSelectedItem().toString();
                itemName[13] = srvType14.getSelectedItem().toString();
                itemName[14] = srvType15.getSelectedItem().toString();
                itemName[15] = srvType16.getSelectedItem().toString();
                itemName[16] = srvType17.getSelectedItem().toString();
                itemName[17] = srvType18.getSelectedItem().toString();
                itemName[18] = srvType19.getSelectedItem().toString();



                for (int i=0 ; i<19 ; i++ )
                {
                    if (nofItem[i].equals("0"))
                    {
                        //nothing
                    }
                    else
                    {
                        itemSelected[i] = getSelectedItems(nofItem[i]);
                    }
                }


                Intent i = new Intent(FreshOrderActivity.this , CheckoutActivity.class);
                i.putExtra("NO_OF_ITEM",itemSelected);
                i.putExtra("SERVICE_TYPE",itemName);

                startActivity(i);
            }
        });

    }

    private int getSelectedItems(String x){
        int y=0;
        if(x.equals("1"))
        {
            y=1;
        }
        else if (x.equals("2"))
        {
            y=2;
        }
        else if (x.equals("3"))
        {
            y=3;
        }
        else if (x.equals("4"))
        {
            y=4;
        }
        else if (x.equals("5"))
        {
            y=5;
        }
        return y;
    }
}
