package com.healthslife.activitys;

import com.healthslife.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class HeartRateResultActivity extends Activity {
	TextView textViewData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_heart_rate_result);
		findView();
		setActionBarLayout();
		setData();
		setCursorProgress(65);
		Toast.makeText(this, "智能家居服务已开启", Toast.LENGTH_LONG).show();;
		
		
	}
	private void setData(){
		Integer i = getIntent().getIntExtra("data", 77);
		textViewData.setText(i.toString());
	}
	
	SeekBar seekBar;
	RelativeLayout cursor;
	RelativeLayout reLayout;
	TextView number;
	private void findView() {
		textViewData = (TextView) findViewById(R.id.textview_activity_heart_rate_result);
		setFONT(textViewData, "H");
		reLayout = (RelativeLayout) findViewById(R.id.activity_heart_rate_result_relativelayout);
		cursor = (RelativeLayout)findViewById(R.id.cursor_seekbar);
		number = (TextView)findViewById(R.id.textview_heart_result);
	}
	private void setCursorProgress(int percent) {
		percent=(int)(percent *7);
		RelativeLayout.LayoutParams lp = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.leftMargin = percent;
		cursor.setLayoutParams(lp);
	}
	
	
	ImageButton open_menu;
	public void setActionBarLayout() {
        ActionBar actionBar = getActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mInflater.inflate(R.layout.new_main_actionbar, null);
            open_menu = (ImageButton) view.findViewById(R.id.sliding_drawer);
            open_menu.setImageDrawable(getResources().getDrawable(R.drawable.return_icon));
            open_menu.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
            TextView viceText = (TextView) view.findViewById(R.id.vice_text);
            viceText.setText("  Heartbeat Rate");
            setFONT(viceText,"S");
            viceText.getPaint().setFakeBoldText(true);
            ActionBar.LayoutParams layout = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(view, layout);
        }
    }
	public void setFONT(TextView t,String s){
		if (s.equals("S")) {
			//"S"
			t.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/SEGOE.TTF"));
		}else{
			//"H"
			t.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/HATTEN.TTF"));	
		}
		
	}
}
