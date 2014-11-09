
package com.healthslife.pedometer.widget;

import com.healthslife.R;
import com.healthslife.pedometer.colorpicker.ColorPickerDialog;
import com.healthslife.pedometer.colorpicker.ColorPickerDialog.OnColorChangedListener;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class WidgetConfig extends Activity implements OnClickListener {

	private static int widgetId;

	@Override
	protected void onPause() {
		super.onPause();
		startService(new Intent(this, WidgetUpdateService.class));
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Intent intent = getIntent();
		final Bundle extras = intent.getExtras();
		if (extras != null) {
			setContentView(R.layout.widgetconfig);

			View textcolor = findViewById(R.id.textcolor);
			textcolor.setOnClickListener(this);
			textcolor.setTag(Color.WHITE);
			textcolor.setBackgroundColor(Color.WHITE);
			View bgcolor = findViewById(R.id.bgcolor);
			bgcolor.setOnClickListener(this);
			bgcolor.setTag(Color.TRANSPARENT);
			bgcolor.setBackgroundColor(Color.TRANSPARENT);

			widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);

			final Intent resultValue = new Intent();
			resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
			setResult(RESULT_OK, resultValue);
		} else {
			finish();
		}
	}

	@Override
	public void onClick(final View v) {
		ColorPickerDialog dialog = new ColorPickerDialog(this,
				(findViewById(v.getId()).getTag() != null) ? (Integer) findViewById(v.getId())
						.getTag() : -1);
		dialog.setHexValueEnabled(true);
		dialog.setAlphaSliderVisible(true);
		dialog.setOnColorChangedListener(new OnColorChangedListener() {
			@Override
			public void onColorChanged(int color) {
				v.setBackgroundColor(color);
				v.setTag(color);
				getSharedPreferences("Widgets", Context.MODE_PRIVATE)
						.edit()
						.putInt((v.getId() == R.id.bgcolor ? "background_" : "color_") + widgetId,
                                color).apply();
			}
		});
		dialog.show();
	}

}
