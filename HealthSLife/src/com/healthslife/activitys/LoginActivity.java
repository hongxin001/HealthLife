package com.healthslife.activitys;


import com.healthslife.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {
	EditText phoneNumber;
	EditText Identify;
	TextView getIdentify;
	Button confirButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
		setContentView(R.layout.activity_login);
		findView();
		onClick();
	}
	
	public void findView(){
		Identify = (EditText) findViewById(R.id.identity);
		phoneNumber = (EditText) findViewById(R.id.phone_number);
		getIdentify = (TextView) findViewById(R.id.get);
		confirButton = (Button) findViewById(R.id.commit_log_in);
	}
	public void onClick() {
		getIdentify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		confirButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoMainActivity();
			}
		});
	}
	
	
	public void gotoMainActivity(){
		startActivity(new Intent(LoginActivity.this,newMainActivity.class));
		finish();
		overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
	}
	
}
