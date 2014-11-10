package com.healthslife.fragments;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.healthslife.R;
import com.healthslife.utils.RequestManager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;


public class BaseFragment extends Fragment{
    private Activity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        RequestManager.cancelAll(this);
    }

    protected void executeRequest(Request<?> request) {
        RequestManager.addRequest(request, this);
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(activity, error.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), R.string.error,Toast.LENGTH_LONG).show();

            }
        };
    }
}
