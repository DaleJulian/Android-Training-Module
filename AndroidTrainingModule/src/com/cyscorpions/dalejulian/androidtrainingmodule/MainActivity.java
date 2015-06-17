package com.cyscorpions.dalejulian.androidtrainingmodule;

import java.util.Date;

import com.cyscorpions.dalejulian.androidtrainingmodule.DatePickerFragment.ReceiveDateDialogListener;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		ReceiveDateDialogListener {

	private Date mDate = new Date();
	private TextView mHeaderTextView;
	private Button mChangeButton;
	private Button mHelloWorldButton;
	private Button mShowDatePickerButton;
	private EditText mCustomGreetingEditText;

	private static final String DIALOG_DATE = "date";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relative_layout_activity);

		mHeaderTextView = (TextView) findViewById(R.id.txtGreeting);
		mChangeButton = (Button) findViewById(R.id.btnChangeGreeting);
		mCustomGreetingEditText = (EditText) findViewById(R.id.txtGetGreeting);
		mHelloWorldButton = (Button) findViewById(R.id.btnDisplayHelloWorld);
		mShowDatePickerButton = (Button) findViewById(R.id.btnSelectDate);

		AddListeners();

	}

	private void AddListeners() {

		mChangeButton.setOnClickListener(clickListener);
		mHelloWorldButton.setOnClickListener(clickListener);
		mShowDatePickerButton.setOnClickListener(clickListener);
	}

	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnChangeGreeting:
				mHeaderTextView.setText(mCustomGreetingEditText.getText()
						.toString());
				break;
			case R.id.btnDisplayHelloWorld:
				mHeaderTextView.setText(R.string.hello_world);
				break;
			case R.id.btnSelectDate:
				FragmentManager fm = getSupportFragmentManager();
				DatePickerFragment dialog = new DatePickerFragment()
						.newInstance(mDate);
				dialog.show(fm, DIALOG_DATE);
				break;

			}
		}
	};

	@Override
	public void onFinishEditDialog(Date date) {
		mDate = date;
		Toast.makeText(getApplicationContext(), mDate.toString(),
				Toast.LENGTH_LONG).show();

	}
}
