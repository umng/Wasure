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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import in.wasure.wasurenew.activity.MainActivity;


public class DashboardFragment extends Fragment {
    public DashboardFragment() {
        // Required empty public constructor
    }

    Button freshOrder,myorders,rateCard;
    TextView header;
    ProgressDialog pd;
    RelativeLayout activity_header;

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

        //my orders
        myorders = (Button) rootView.findViewById(R.id.activity_main_myOrdersButton);
        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyOrdersActivity.class));
            }
        });

        //rate card
        rateCard = (Button) rootView.findViewById(R.id.activity_main_rateCardButton);
        rateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RateCard.class));
            }
        });

        //fetch stHeader info
        header = (TextView) rootView.findViewById(R.id.dashboard_header);
        activity_header = (RelativeLayout) rootView.findViewById(R.id.activity_dashboard_header);
        if(user.getBoolean("emailVerified")!=true)
        {
            header.setText(user.getString("firstName").toString() + ", we want to know you better.");
            activity_header.setVisibility(View.VISIBLE);
        }
        else
        {
            stHeader="";
            stHeader += "Hey " + user.getString("firstName").toString() + ",\n";
//            stHeader += "User name : " + user.getUsername().toString() + "\n";
//            stHeader += "Email : " + user.getEmail().toString() + "\n";
//            stHeader += "Phone : " + user.getString("phone").toString() + "\n";
            header.setText(stHeader);
        }

        user.fetchInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // Success!
                } else {
                    // Failure!
                }
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
