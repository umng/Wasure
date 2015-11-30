package in.wasure.wasurenew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import in.wasure.wasurenew.adapter.MyRecyclerViewAdapter;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    ProgressDialog pd;
    int total, index, x=0;
    ArrayList results;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        results = new ArrayList<DataObject>();
        index = 0;

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Processing");
        pd.setCancelable(false);
        pd.show();

        ParseQuery orderQuery = new ParseQuery("Orders");
        orderQuery.whereEqualTo("username", ParseUser.getCurrentUser());
        orderQuery.orderByDescending("createdAt");
        orderQuery.setLimit(2);
        orderQuery.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> content, ParseException pEx) {
                // TODO Auto-generated method stub
                if (pEx == null && content != null) {
                    if (!(content.isEmpty())) {
                        for (ParseObject orderObject : content) {
                            final ParseObject orderId = orderObject;


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

                                        DataObject obj = new DataObject(orderId.get("orderId").toString() + index,
                                                "Secondary " + index, "Total: " + String.valueOf(total) + " Rs." + index);
                                        results.add(index, obj);
                                        index++;
                                    } else {
                                        Toast.makeText(getActivity(), "Error in fetching total amount try again!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                        }
                    } else {
                        Toast.makeText(getActivity(), "Error in fetching orders try again!", Toast.LENGTH_LONG).show();
                    }
                    x=1;
                }
            }
        });

//        for (int index = 0; index < 20; index++) {
//            DataObject obj = new DataObject("Some Primary Text \n" + index,
//                    "Secondary " + index, "Some Tertiary Text\n " + index);
//            results.add(index, obj);
//        }
        pd.dismiss();
        return results;
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