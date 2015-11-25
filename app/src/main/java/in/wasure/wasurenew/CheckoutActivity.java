package in.wasure.wasurenew;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by Umang on 11/18/2015.
 */
public class CheckoutActivity extends AppCompatActivity {

    ProgressDialog pd;
    EditText name,room,phone;
    Spinner hostel,block;
    TextView header;
    LinearLayout activity_header, activity;
    Button order,cancel;

    ParseUser user = ParseUser.getCurrentUser();

    private int x=0, y=0;
    private String List="";
    private String[] itemName= {"Pant                ","Shirt               ",
            "Jeans               ","T-Shirt             ","Jacket            ",
            "Blanket            ","Night Pant      ", "Ladies Top     ","Ladies Dress  ",
            "Skirt                ","Capries          ","Slacks             ","Bed Sheet     ",
            "Pillow Cover ", "Bag (Medium)","Bag (Large)    ","Sweater         ",
            "Towel              "};
    private int[] itemSelected;
    String[] srvType;
    String phoneOrder, hostelOrder, blockOrder, roomOrder;
    String phoneSelected, hostelSelected, blockSelected, roomSelected;
    String userDetailsObjectId;
    ParseObject orderId = new ParseObject("Orders");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        itemSelected = intent.getIntArrayExtra("NO_OF_ITEM");
        srvType = intent.getStringArrayExtra("SERVICE_TYPE");

        for (int i=0; i<18; i++)
        {
            if(itemSelected[i]!=0)
            {
                List += itemName[i] + "\t\t" + itemSelected[i] + "\t\t\t\t\t\t" + srvType[i] + "\n";
            }
        }
        TextView t = (TextView) findViewById(R.id.nameList);
        t.setText(List);


        pd = new ProgressDialog(CheckoutActivity.this);
        pd.setMessage("Processing");
        pd.setCancelable(false);
        pd.show();

        name = (EditText) findViewById(R.id.activity_checkout_name);
        room =(EditText) findViewById(R.id.activity_checkout_room);
        phone = (EditText) findViewById(R.id.activity_checkout_phone);
        hostel = (Spinner) findViewById(R.id.activity_checkout_hostel);
        block = (Spinner) findViewById(R.id.activity_checkout_block);
        header = (TextView) findViewById(R.id.header);
        activity_header = (LinearLayout) findViewById(R.id.activity_checkout_header);
        activity = (LinearLayout) findViewById(R.id.main);
        order = (Button) findViewById(R.id.activity_checkout_order);
        cancel = (Button) findViewById(R.id.activity_checkout_cancel);

        if(user.getBoolean("emailVerified")!=true)
        {
            header.setText("Sorry, " + user.getString("firstName").toString() + ".\nPlease verify your account first.");
            activity.setVisibility(View.INVISIBLE);
            activity_header.setVisibility(View.VISIBLE);
            pd.dismiss();
        }
        else
        {
            user.fetchInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        // Success!
                        if (x == 1) {
                            pd.dismiss();
                        } else {
                            x = 1;
                        }
                    } else {
                        // Failure!
                    }
                }
            });

            //set name and phone
            name.setText(user.getString("firstName").toString()
                    + " "
                    + user.getString("lastName").toString());

            //set phone
            phoneSelected = user.getString("phone");
            phone.setText(phoneSelected);

            ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
            query.whereEqualTo("username", user);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    userDetailsObjectId = object.getObjectId();
                    if (object == null) {
                        Log.d("score", "The getFirst for hostel request failed.");
                    } else {
                        hostelSelected = object.getString("hostel").toString();
                        blockSelected = object.getString("block").toString();

                        //set room
                        roomSelected = object.getString("room").toString();
                        room.setText(roomSelected);

                        //select hostel spinner
                        if (hostelSelected.equals("Apartments")) {
                            hostel.setSelection(0);
                        } else if (hostelSelected.equals("Boys Hostel-1")) {
                            hostel.setSelection(1);
                        } else if (hostelSelected.equals("Boys Hostel-2")) {
                            hostel.setSelection(2);
                        } else if (hostelSelected.equals("Boys Hostel-3")) {
                            hostel.setSelection(3);
                        } else if (hostelSelected.equals("Boys Hostel-4")) {
                            hostel.setSelection(4);
                        } else if (hostelSelected.equals("Boys Hostel-5")) {
                            hostel.setSelection(5);
                        } else if (hostelSelected.equals("Boys Hostel-6")) {
                            hostel.setSelection(6);
                        } else if (hostelSelected.equals("Boys Hostel-7")) {
                            hostel.setSelection(7);
                        }

                        //select block spinner
                        if (blockSelected.equals("None")) {
                            block.setSelection(0);
                        } else if (blockSelected.equals("A")) {
                            block.setSelection(1);
                        } else if (blockSelected.equals("B")) {
                            block.setSelection(2);
                        } else if (blockSelected.equals("C")) {
                            block.setSelection(3);
                        } else if (blockSelected.equals("D")) {
                            block.setSelection(4);
                        } else if (blockSelected.equals("E")) {
                            block.setSelection(5);
                        }

                        //pd dismiss
                        if (x == 1) {
                            pd.dismiss();
                        } else {
                            x = 1;
                        }
                    }
                }
            });


            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pd.show();

                    //save new address
                    room =(EditText) findViewById(R.id.activity_checkout_room);
                    phone = (EditText) findViewById(R.id.activity_checkout_phone);
                    hostel = (Spinner) findViewById(R.id.activity_checkout_hostel);
                    block = (Spinner) findViewById(R.id.activity_checkout_block);
                    roomOrder = room.getText().toString();
                    phoneOrder = phone.getText().toString();
                    hostelOrder = hostel.getSelectedItem().toString();
                    blockOrder = block.getSelectedItem().toString();

                    if(roomOrder==roomSelected && blockOrder==blockSelected
                            && hostelOrder==hostelSelected && phoneOrder==phoneSelected)
                    {
                        //nothing
                    }
                    else
                    {
                        //set phone
                        user.put("phone", phoneOrder);
                        user.saveInBackground(new SaveCallback() {
                            public void done(com.parse.ParseException e) {
                                if (e == null) {
                                    //
                                } else {
                                    //
                                }
                            }
                        });

                        //set details
                        ParseQuery<ParseObject> queryAddress = ParseQuery.getQuery("UserDetails");
                        queryAddress.getInBackground(userDetailsObjectId, new GetCallback<ParseObject>() {
                            public void done(ParseObject addressObject, ParseException e) {
                                if (e == null) {
                                    addressObject.put("hostel", hostelOrder);
                                    addressObject.put("block", blockOrder);
                                    addressObject.put("room", roomOrder);
                                    addressObject.saveInBackground();
                                }
                            }
                        });
                    }

                    //save order
                    ParseObject orderNow = new ParseObject("Orders");
                    orderNow.put("username", user);
                    orderNow.put("status", "Pending");
                    orderNow.put("address", roomOrder + ", " + hostelOrder + ", Block "
                            + blockOrder );
                    orderNow.put("phone", phoneOrder);
                    orderNow.saveInBackground();
                    orderId = orderNow;

                    //SUCCESSFUL ORDER
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(CheckoutActivity.this);
                    builder1.setMessage("Your order has been successfully ordered.");
                    builder1.setCancelable(false);
                    builder1.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    finish();
                                }
                            });
                    final AlertDialog alert11 = builder1.create();

                    //save items
                    for (int i=0; i<18; i++)
                    {
                        if(itemSelected[i]!=0)
                        {
                            ParseObject orderedItem = new ParseObject("ItemsOrdered");
                            orderedItem.put("orderId", orderId);
                            orderedItem.put("itemName", itemName[i]);
                            orderedItem.put("rate", 10);
                            orderedItem.put("numberItems", itemSelected[i]);
                            orderedItem.put("srvType", srvType[i]);
                            orderedItem.saveInBackground(new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {

                                    } else {
                                        Toast.makeText(CheckoutActivity.this,"Error in ordering try again!",Toast.LENGTH_LONG).show();
                                        pd.dismiss();
                                        y=1;
                                    }
                                }
                            });
                        }
                        if(y==0)
                        {
                            pd.dismiss();

                            alert11.show();
                        }
                    }

                }
            });

        }

    }
}
