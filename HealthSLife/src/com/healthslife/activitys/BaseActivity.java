package com.healthslife.activitys;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.healthslife.R;
import com.healthslife.utils.RequestManager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
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
	                Toast.makeText(getApplicationContext(), R.string.error,Toast.LENGTH_LONG).show();
	            }


	        };
	    }
	
}
