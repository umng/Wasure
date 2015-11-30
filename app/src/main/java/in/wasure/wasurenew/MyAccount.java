package in.wasure.wasurenew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import in.wasure.wasurenew.activity.MainActivity;


public class MyAccount extends Fragment {

    ProgressDialog pd;
    private EditText inputEmail, pwd, fName, lName, phone, room;
    Spinner hostel, block;
    Button editDetails, saveDetails;
    ParseUser user = ParseUser.getCurrentUser();

    String hostelText, blockText, userDetailsObjectId;

    public MyAccount() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_account, container, false);

        inputEmail = (EditText) rootView.findViewById(R.id.activity_account_email);
        pwd = (EditText) rootView.findViewById(R.id.activity_account_password);
        fName = (EditText) rootView.findViewById(R.id.activity_account_firstName);
        lName = (EditText) rootView.findViewById(R.id.activity_account_lastName);
        phone = (EditText) rootView.findViewById(R.id.activity_account_phone);
        room =(EditText) rootView.findViewById(R.id.activity_account_room);
        hostel = (Spinner) rootView.findViewById(R.id.activity_account_hostel);
        block = (Spinner) rootView.findViewById(R.id.activity_account_block);
        editDetails = (Button) rootView.findViewById(R.id.activity_account_EditDetailsButton);
        saveDetails = (Button) rootView.findViewById(R.id.activity_account_accountButton);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Processing");
        pd.setCancelable(false);
        pd.show();

        user.fetchInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // Success!
                } else {
                    // Failure!
                    Toast.makeText(getActivity(), "Can't connect to the internet\n Try again later", Toast.LENGTH_LONG);
                }
            }
        });

        //fetch address
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
        query.whereEqualTo("username", user);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                userDetailsObjectId = object.getObjectId();
                if (object == null) {
                    Log.d("MyAccount", "The getFirst for hostel request failed.");
                    pd.dismiss();
                    Toast.makeText(getActivity(), "Can't connect to the internet\n Try again later", Toast.LENGTH_LONG);
                } else {
                    hostelText = object.getString("hostel").trim();
                    blockText = object.getString("block").trim();

                    //set room
                    room.setText(object.getString("room"));

                    //select hostel spinner
                    if (hostelText.equals("Apartments")) {
                        hostel.setSelection(0);
                    } else if (hostelText.equals("Boys Hostel-1")) {
                        hostel.setSelection(1);
                    } else if (hostelText.equals("Boys Hostel-2")) {
                        hostel.setSelection(2);
                    } else if (hostelText.equals("Boys Hostel-3")) {
                        hostel.setSelection(3);
                    } else if (hostelText.equals("Boys Hostel-4")) {
                        hostel.setSelection(4);
                    } else if (hostelText.equals("Boys Hostel-5")) {
                        hostel.setSelection(5);
                    } else if (hostelText.equals("Boys Hostel-6")) {
                        hostel.setSelection(6);
                    } else if (hostelText.equals("Boys Hostel-7")) {
                        hostel.setSelection(7);
                    } else if (hostelText.equals("Girls Hostel-1")) {
                        hostel.setSelection(8);
                    } else if (hostelText.equals("Girls Hostel-2")) {
                        hostel.setSelection(9);
                    } else if (hostelText.equals("Girls Hostel-3")) {
                        hostel.setSelection(10);
                    } else if (hostelText.equals("Girls Hostel-4")) {
                        hostel.setSelection(11);
                    } else if (hostelText.equals("Girls Hostel-5")) {
                        hostel.setSelection(12);
                    } else if (hostelText.equals("Girls Hostel-6")) {
                        hostel.setSelection(13);
                    } else if (hostelText.equals("Uni Hotel")) {
                        hostel.setSelection(14);
                    }

                    //select block spinner
                    if (blockText.equals("None")) {
                        block.setSelection(0);
                    } else if (blockText.equals("A")) {
                        block.setSelection(1);
                    } else if (blockText.equals("B")) {
                        block.setSelection(2);
                    } else if (blockText.equals("C")) {
                        block.setSelection(3);
                    } else if (blockText.equals("D")) {
                        block.setSelection(4);
                    } else if (blockText.equals("E")) {
                        block.setSelection(5);
                    }
                    pd.dismiss();
                }
            }
        });

        inputEmail.setText(user.getEmail());
        pwd.setText("12345678");
        fName.setText(user.getString("firstName"));
        lName.setText(user.getString("lastName"));
        phone.setText(user.getString("phone"));

        inputEmail.setEnabled(false);
        pwd.setEnabled(false);
        fName.setEnabled(false);
        lName.setEnabled(false);
        phone.setEnabled(false);
        room.setEnabled(false);
        hostel.setEnabled(false);
        block.setEnabled(false);

        editDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fName.setEnabled(true);
                lName.setEnabled(true);
                phone.setEnabled(true);
                room.setEnabled(true);
                hostel.setEnabled(true);
                block.setEnabled(true);
                editDetails.setVisibility(View.GONE);
                saveDetails.setVisibility(View.VISIBLE);
            }
        });

        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();

                user.put("firstName", fName.getText().toString().trim());
                user.put("lastName", lName.getText().toString().trim());
                user.put("phone", phone.getText().toString().trim());
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null)
                        {
                            //success
                        }
                        else
                        {
                            pd.dismiss();
                            Toast.makeText(getActivity(), "Can't connect to the internet\n Try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                //set details
                ParseQuery<ParseObject> queryAddress = ParseQuery.getQuery("UserDetails");
                queryAddress.getInBackground(userDetailsObjectId, new GetCallback<ParseObject>() {
                    public void done(ParseObject addressObject, ParseException e) {
                        if (e == null) {
                            addressObject.put("hostel", hostel.getSelectedItem().toString().trim());
                            addressObject.put("block", block.getSelectedItem().toString().trim());
                            addressObject.put("room", room.getText().toString().trim());
                            addressObject.saveInBackground();
                            pd.dismiss();
                            Toast.makeText(getActivity(), "Details Updated Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            pd.dismiss();
                            Toast.makeText(getActivity(), "Can't connect to the internet\n Try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
