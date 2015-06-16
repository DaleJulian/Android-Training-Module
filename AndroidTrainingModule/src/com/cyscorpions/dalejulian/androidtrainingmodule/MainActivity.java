package com.cyscorpions.dalejulian.androidtrainingmodule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends Activity {

	
	private CheckBox mCheckBox;
	private TextView mTextView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mCheckBox = (CheckBox)findViewById(R.id.main_checkbox);
        mTextView = (TextView)findViewById(R.id.main_textView);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
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
}
