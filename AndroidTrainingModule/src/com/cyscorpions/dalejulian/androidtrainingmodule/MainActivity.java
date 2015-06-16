package com.cyscorpions.dalejulian.androidtrainingmodule;

import java.util.Date;

import com.cyscorpions.dalejulian.androidtrainingmodule.DatePickerFragment.ReceiveDateDialogListener;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ReceiveDateDialogListener {

	private Date mDate = new Date();
	private TextView mTextView;
	private Button mChangeButton;
	private Button mHelloWorldButton;
	private Button mShowDatePickerButton;
	private EditText mEditText;

	private static final String DIALOG_DATE = "date";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relative_layout_activity);

		mTextView = (TextView) findViewById(R.id.main_textView);
		mChangeButton = (Button) findViewById(R.id.main_changeGreet_button);
		mEditText = (EditText) findViewById(R.id.main_editText);
		mHelloWorldButton = (Button) findViewById(R.id.main_displayHelloWorld_button);
		mShowDatePickerButton = (Button) findViewById(R.id.main_showDatePicker_button);

		mChangeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mTextView.setText(mEditText.getText().toString());

			}
		});

		mHelloWorldButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mTextView.setText("Hello, world!");

			}
		});

		mShowDatePickerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = getSupportFragmentManager();
				DatePickerFragment dialog = new DatePickerFragment().newInstance(mDate);
				dialog.show(fm, DIALOG_DATE);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onFinishEditDialog(Date date) {
		mDate = date;
		Toast.makeText(getApplicationContext(), mDate.toString(),
				Toast.LENGTH_LONG).show();

	}
}
