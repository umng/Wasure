package in.wasure.wasurenew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;


public class DashboardFragment extends Fragment {
    public DashboardFragment() {
        // Required empty public constructor
    }

    Button freshOrder,myorders;
    TextView header;
    ProgressDialog pd;

    private String stHeader ="";

    ParseUser user = ParseUser.getCurrentUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);


        //freshorder
        freshOrder = (Button) rootView.findViewById(R.id.activity_main_freshOrderButton);
        freshOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),FreshOrderActivity.class);
                startActivity(in);
            }
        });

        myorders = (Button) rootView.findViewById(R.id.activity_main_myOrdersButton);
        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyOrdersActivity.class));
            }
        });

        //fetch stHeader info
        header = (TextView) rootView.findViewById(R.id.activity_main_header);
        if(user.getBoolean("emailVerified")!=true)
        {
            header.setText("Sorry, " + user.getString("firstName").toString() + ".\nPlease verify your account first.");
        }
        else
        {
            stHeader="";
            stHeader += "Hey " + user.getString("firstName").toString() + ",\n";
            stHeader += "User name : " + user.getUsername().toString() + "\n";
            stHeader += "Email : " + user.getEmail().toString() + "\n";
            stHeader += "Phone : " + user.getString("phone").toString() + "\n";
            header.setText(stHeader);
        }

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
