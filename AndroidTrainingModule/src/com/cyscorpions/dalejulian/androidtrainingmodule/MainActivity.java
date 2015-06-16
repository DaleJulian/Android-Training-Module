package com.cyscorpions.dalejulian.androidtrainingmodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

	private CheckBox mCheckBox;
	private TextView mTextView;
	private Button mNextActivityButton;

	private static final int RESULT_CODE = 1;
	private static final String TAG = "MainActivity";
	public static final String MAIN_ACTIVITY_EXTRA = "com.cyscorpions.dalejulian.androidtrainingmodule.MainActivity.EXTRA";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mCheckBox = (CheckBox) findViewById(R.id.main_checkbox);
		mTextView = (TextView) findViewById(R.id.main_textView);
		mNextActivityButton = (Button) findViewById(R.id.main_nextActivity_button);
		mNextActivityButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, NewActivity.class);
				intent.putExtra(MAIN_ACTIVITY_EXTRA, "Hello, world!");
				startActivityForResult(intent, 1);

			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				String result = data
						.getStringExtra(NewActivity.NEW_ACTIVITY_EXTRA);
				mTextView.setText(result);
			}

		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.setGroupVisible(1, mCheckBox.isChecked());
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, 1, 0, "add");
		menu.add(0, 2, 0, "edit");
		menu.add(0, 3, 3, "delete");
		menu.add(1, 4, 1, "copy");
		menu.add(1, 5, 2, "paste");
		menu.add(1, 6, 4, "exit");

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		StringBuilder sb = new StringBuilder();

		// print the info about pressed menu item
		sb.append("Item Menu");
		sb.append("\r\n groupId: " + String.valueOf(item.getGroupId()));
		sb.append("\r\n itemId: " + String.valueOf(item.getItemId()));
		sb.append("\r\n order: " + String.valueOf(item.getOrder()));
		sb.append("\r\n title: " + item.getTitle());
		mTextView.setText(sb.toString());
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "MainActivity: onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "MainActivity: onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "MainActivity: onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "MainActivity: onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "MainActivity: onDestroy()");
	}
}
