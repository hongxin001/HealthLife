package com.healthslife.activitys;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.healthslife.R;
import com.healthslife.activitys.BaseActivity;
import com.healthslife.activitys.newMainActivity;
import com.healthslife.setting.AppSetting;
import com.healthslife.utils.Configs;
import com.healthslife.utils.String2Request;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
	EditText phoneNumber;
	EditText Identify;
	TextView getIdentify;
	Button confirButton;
	private String token;
	private AppSetting setting;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
		setContentView(R.layout.activity_login);
		token = "";
		setting = new AppSetting(this);
		findView();
		onClick();
	}

	public void findView() {
		Identify = (EditText) findViewById(R.id.identity);
		phoneNumber = (EditText) findViewById(R.id.phone_number);
		getIdentify = (TextView) findViewById(R.id.get);
		confirButton = (Button) findViewById(R.id.commit_log_in);
	}

	public void onClick() {
		getIdentify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				executeRequest(new String2Request(Configs.GET_TOKEN, "utf-8",
						responseListener(), errorListener()) {
					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						// TODO Auto-generated method stub
						Map<String, String> m = new HashMap<String, String>();
						m.put("telnum", phoneNumber.getText().toString());
						return m;
					}
				});
			}
		});

		confirButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				token = Identify.getText().toString();
				if (!token.equals("")) {
					executeRequest(new String2Request(Configs.VERIFY_TOKEN,
							"utf-8", suresponseListener(), errorListener()) {
						@Override
						protected Map<String, String> getParams()
								throws AuthFailureError {
							// TODO Auto-generated method stub
							Map<String, String> m = new HashMap<String, String>();
							m.put("telnum", phoneNumber.getText().toString());
							m.put("token", token);
							Log.v("token",token);
							return m;
						}
					});
				}else{
					Toast.makeText(getApplicationContext(), "token null",
							Toast.LENGTH_LONG).show();
				}

			}
		});
	}

	public void gotoMainActivity() {
		startActivity(new Intent(LoginActivity.this, newMainActivity.class));
		finish();
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	private Response.Listener<String> responseListener() {
		return new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {
				Log.v("message", s);
			}
		};
	}

	private Response.Listener<String> suresponseListener() {
		return new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {
				Log.v("message", s);
				if (s.equals("1")) {
					setting.setTelNUM(phoneNumber.getText().toString());
					gotoMainActivity();
				} else {
					Toast.makeText(getApplicationContext(), "token error",
							Toast.LENGTH_LONG).show();
				}
			}
		};
	}

}
