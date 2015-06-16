package com.cyscorpions.dalejulian.androidtrainingmodule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewActivity extends Activity {
	public static final String NEW_ACTIVITY_EXTRA = "com.cyscorpions.dalejulian.android.NewActivity.Extra";

	private TextView mTextColor, mTextSize, mHeader;
	private Button mBackToMainButton;

	final int MENU_COLOR_RED = 1;
	final int MENU_COLOR_GREEN = 2;
	final int MENU_COLOR_BLUE = 3;

	final int MENU_SIZE_22 = 4;
	final int MENU_SIZE_26 = 5;
	final int MENU_SIZE_30 = 6;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.context_menu_activity);

		mTextColor = (TextView) findViewById(R.id.context_textColor);
		mTextSize = (TextView) findViewById(R.id.context_textViewSize);
		mHeader = (TextView) findViewById(R.id.context_header_textView);

		mHeader.setText(getIntent().getExtras().getString(
				MainActivity.MAIN_ACTIVITY_EXTRA));

		mBackToMainButton = (Button) findViewById(R.id.context_backToMainActivity_button);
		mBackToMainButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(NEW_ACTIVITY_EXTRA, "Hi world!");
				setResult(RESULT_OK, intent);
				finish();

			}
		});

		registerForContextMenu(mTextColor);
		registerForContextMenu(mTextSize);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo info) {

		switch (v.getId()) {
		case R.id.context_textColor:
			menu.add(0, MENU_COLOR_RED, 0, "Red");
			menu.add(0, MENU_COLOR_GREEN, 0, "Green");
			menu.add(0, MENU_COLOR_BLUE, 0, "Blue");
			break;
		case R.id.context_textViewSize:
			menu.add(0, MENU_SIZE_22, 0, "22");
			menu.add(0, MENU_SIZE_26, 0, "26");
			menu.add(0, MENU_SIZE_30, 0, "30");
			break;
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		// menu items for textColor
		case MENU_COLOR_RED:
			mTextColor.setTextColor(Color.RED);
			mTextColor.setText("Text color = red");
			break;
		case MENU_COLOR_GREEN:
			mTextColor.setTextColor(Color.GREEN);
			mTextColor.setText("Text color = green");
			break;
		case MENU_COLOR_BLUE:
			mTextColor.setTextColor(Color.BLUE);
			mTextColor.setText("Text color = blue");
			break;
		// menu items for textSize
		case MENU_SIZE_22:
			mTextSize.setTextSize(22);
			mTextSize.setText("Text size = 22");
			break;
		case MENU_SIZE_26:
			mTextSize.setTextSize(26);
			mTextSize.setText("Text size = 26");
			break;
		case MENU_SIZE_30:
			mTextSize.setTextSize(30);
			mTextSize.setText("Text size = 30");
			break;
		}
		return super.onContextItemSelected(item);
	}
}
