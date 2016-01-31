package in.wasure.wasurenew;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import in.wasure.wasurenew.activity.MainActivity;

/**
 * Created by Umang on 11/18/2015.
 */
public class CheckoutActivity extends AppCompatActivity {

    ProgressDialog pd;
    EditText name,room,phone;
    Spinner hostel,block;
    TextView header;
    LinearLayout activity;
    RelativeLayout activity_header;
    Button order,cancel,verify;
    Toolbar mToolbar;

    ParseUser user = ParseUser.getCurrentUser();

    private int x=0, y=0, z=0, i=0;
    private String List="";
    private String[] itemNameforShow= {"Pant                ","Shirt               ",
            "Jeans               ","T-Shirt             ","Jacket            ",
            "Blanket            ","Night Pant      ","Shorts               ","Ladies Top     ",
            "Ladies Dress  ","Skirt                ","Capris            ","Slacks             ",
            "Bed Sheet     ","Pillow Cover ", "Bag (Medium)","Bag (Large)    ","Sweater         ",
            "Towel              "};
    private String[] itemName= {"Pant", "Shirt", "Jeans", "T-Shirt", "Jacket", "Blanket"
            , "Night Pant", "Shorts", "Ladies Top", "Ladies Dress", "Skirt", "Capris", "Slacks"
            , "Bed Sheet", "Pillow Cover", "Bag (Medium)", "Bag (Large)", "Sweater", "Towel"};
    private int[][] rate={{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            , {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            , {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            , {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    private int[] itemSelected;
    private String[] srvType;
    String nameOrder, phoneOrder, hostelOrder, blockOrder, roomOrder;
    String phoneSelected, hostelSelected, blockSelected, roomSelected;
    String userDetailsObjectId;
    ParseObject orderId = new ParseObject("Orders");
    int r= 0;
    boolean setOk = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_freshorder);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Checkout Order");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        itemSelected = intent.getIntArrayExtra("NO_OF_ITEM");
        srvType = intent.getStringArrayExtra("SERVICE_TYPE");


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
        activity_header = (RelativeLayout) findViewById(R.id.activity_checkout_header);
        activity = (LinearLayout) findViewById(R.id.main);
        verify= (Button) findViewById(R.id.activity_checkout_verify);
        order = (Button) findViewById(R.id.activity_checkout_order);
        cancel = (Button) findViewById(R.id.activity_checkout_cancel);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"));
                PackageManager pm = getPackageManager();

                List<ResolveInfo> resInfo = pm.queryIntentActivities(emailIntent, 0);
                if (resInfo.size() > 0) {
                    ResolveInfo ri = resInfo.get(0);
                    // First create an intent with only the package name of the first registered email app
                    // and build a picked based on it
                    Intent intentChooser = pm.getLaunchIntentForPackage(ri.activityInfo.packageName);
                    Intent openInChooser =
                            Intent.createChooser(intentChooser, "Verify using");

                    // Then create a list of LabeledIntent for the rest of the registered email apps
                    List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
                    for (int i = 1; i < resInfo.size(); i++) {
                        // Extract the label and repackage it in a LabeledIntent
                        ri = resInfo.get(i);
                        String packageName = ri.activityInfo.packageName;
                        Intent intent = pm.getLaunchIntentForPackage(packageName);
                        intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                    }

                    LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
                    // Add the rest of the email apps to the picker selection
                    openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                    startActivity(openInChooser);
                }
                else{
                    AlertDialog.Builder builder5 = new AlertDialog.Builder(CheckoutActivity.this);
                    builder5.setMessage("You do not have any email client installed.");
                    builder5.setCancelable(false);
                    builder5.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    final AlertDialog alert5 = builder5.create();
                    alert5.show();
                }
            }
        });

        if(user.getBoolean("emailVerified")!=true)
        {
            header.setText(user.getString("firstName").toString() + ", we want to know you better.");
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

            //print order info
            for (int i=0; i<19; i++)
            {
                if(itemSelected[i]!=0)
                {
                    List += itemNameforShow[i] + "\t\t" + itemSelected[i] + "\t\t\t\t\t\t" + srvType[i] + "\n";
                }
            }
            TextView t = (TextView) findViewById(R.id.nameList);
            t.setText(List);

            //set name
            name.setText(user.getString("firstName").toString()
                    + " "
                    + user.getString("lastName").toString());

            //set phone
            phoneSelected = user.getString("phone");
            phone.setText(phoneSelected);

            //get rate of items
            i = 0;
            final ParseQuery ratequery = new ParseQuery("Price");
            ratequery.whereNotEqualTo("itemName", "");
            ratequery.orderByAscending("createdAt");
            ratequery.findInBackground(new FindCallback<ParseObject>() {

                @Override
                public void done(java.util.List<ParseObject> content, ParseException pEx) {
                    // TODO Auto-generated method stub
                    if (pEx == null && content != null) {
                        for (ParseObject rateObject : content) {
                            rate[0][i] = rateObject.getInt("wash_iron");
                            rate[1][i] = rateObject.getInt("wash");
                            rate[2][i] = rateObject.getInt("iron");
                            rate[3][i] = rateObject.getInt("dryclean");
                            if (i == 18) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        z = 1;
                    } else {
                        Toast.makeText(CheckoutActivity.this, "Error in fetching rate for items!", Toast.LENGTH_LONG).show();
                    }
                }
            });

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
                                                   } else if (hostelSelected.equals("Girls Hostel-1")) {
                                                       hostel.setSelection(8);
                                                   } else if (hostelSelected.equals("Girls Hostel-2")) {
                                                       hostel.setSelection(9);
                                                   } else if (hostelSelected.equals("Girls Hostel-3")) {
                                                       hostel.setSelection(10);
                                                   } else if (hostelSelected.equals("Girls Hostel-4")) {
                                                       hostel.setSelection(11);
                                                   } else if (hostelSelected.equals("Girls Hostel-5")) {
                                                       hostel.setSelection(12);
                                                   } else if (hostelSelected.equals("Girls Hostel-6")) {
                                                       hostel.setSelection(13);
                                                   } else if (hostelSelected.equals("Uni Hotel")) {
                                                       hostel.setSelection(14);
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
                                       }

            );


            cancel.setOnClickListener(new View.OnClickListener()

                                      {
                                          @Override
                                          public void onClick(View v) {
                                              finish();
                                          }
                                      }

            );

            order.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {

                    setOk = true;

                    pd.show();

                    //close checkout activity on not able to fetch rate
                    if (z == 0) {
                        finish();
                    }

                    //save new address
                    room = (EditText) findViewById(R.id.activity_checkout_room);
                    phone = (EditText) findViewById(R.id.activity_checkout_phone);
                    hostel = (Spinner) findViewById(R.id.activity_checkout_hostel);
                    block = (Spinner) findViewById(R.id.activity_checkout_block);
                    roomOrder = room.getText().toString();
                    nameOrder = name.getText().toString();
                    phoneOrder = phone.getText().toString();
                    hostelOrder = hostel.getSelectedItem().toString();
                    blockOrder = block.getSelectedItem().toString();

                    if (roomOrder.equals("") || blockOrder.equals("")
                            || hostelOrder.equals("") || phoneOrder.equals("")) {
                        setOk = false;
                        AlertDialog.Builder builder12 = new AlertDialog.Builder(CheckoutActivity.this);
                        builder12.setMessage("Please fill up all the information.");
                        builder12.setCancelable(true);
                        builder12.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        return;
                                    }
                                });
                        final AlertDialog alert12 = builder12.create();
                        pd.dismiss();
                        alert12.show();
                    } else if (roomOrder.equals(roomSelected) && blockOrder.equals(blockSelected)
                            && hostelOrder.equals(hostelSelected) && phoneOrder.equals(phoneSelected)) {
                        //nothing
                    } else {
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

                    if (setOk == true) {
                        //SUCCESSFUL ORDER
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(CheckoutActivity.this);
                        builder1.setMessage("Your order has been successfully ordered.");
                        builder1.setCancelable(false);
                        builder1.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                        Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                });
                        final AlertDialog alert11 = builder1.create();

                        //save order
                        ParseObject orderNow = new ParseObject("Orders");
                        orderNow.put("username", user);
                        orderNow.put("status", "Verifying");
                        orderNow.put("address", roomOrder + ", " + hostelOrder + ", Block "
                                + blockOrder);
                        orderNow.put("phone", phoneOrder);
                        orderNow.put("nameOfPerson", nameOrder);
                        orderNow.saveInBackground(new SaveCallback() {
                            public void done(com.parse.ParseException e) {
                                if (e == null) {
                                    if (y == 2) {
                                        pd.dismiss();
                                        alert11.show();
                                    } else {
                                        y++;
                                    }
                                } else {
                                    //
                                }
                            }
                        });
                        orderId = orderNow;

                        //save items
                        for (int i = 0; i < 19; i++) {
                            if (itemSelected[i] != 0) {

                                //set rateSubmit
                                if (srvType[i].equals("Wash/Iron")) {
                                    r = 0;
                                } else if (srvType[i].equals("Wash")) {
                                    r = 1;
                                } else if (srvType[i].equals("Iron")) {
                                    r = 2;
                                } else if (srvType[i].equals("Dry Clean")) {
                                    r = 3;
                                }

                                ParseObject orderedItem = new ParseObject("ItemsOrdered");
                                orderedItem.put("orderId", orderId);
                                orderedItem.put("itemName", itemName[i]);
                                orderedItem.put("rate", rate[r][i]);
                                orderedItem.put("numberItems", itemSelected[i]);
                                orderedItem.put("srvType", srvType[i]);
                                orderedItem.saveInBackground(new SaveCallback() {
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            if (y == 2) {
                                                pd.dismiss();
                                                alert11.show();
                                            } else {
                                                y++;
                                            }
                                        } else {

                                            Toast.makeText(CheckoutActivity.this, "Error in ordering try again!", Toast.LENGTH_LONG).show();
                                            pd.dismiss();
                                            y++;
                                        }
                                    }
                                });
                            }
                        }

                        if (y == 2) {
                            pd.dismiss();
                            alert11.show();
                        } else {
                            y++;
                        }
                    }

                }
            });

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
