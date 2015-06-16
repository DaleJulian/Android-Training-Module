package com.cyscorpions.dalejulian.androidtrainingmodule;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "https://api.myjson.com/bins/3alxc";

	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_MOBILE = "mobile";
	private static final String TAG_IMG_LINK = "imglink";

	private ImageView mImageView;

	// contacts JSONArray
	JSONArray contacts = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		contactList = new ArrayList<HashMap<String, String>>();

		ListView lv = getListView();

		// Listview on item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
//				String name = ((TextView) view.findViewById(R.id.name))
//						.getText().toString();
//				String cost = ((TextView) view.findViewById(R.id.email))
//						.getText().toString();
//				String description = ((TextView) view.findViewById(R.id.mobile))
//						.getText().toString();
			}
		});
		
		//mImageView = (ImageView)findViewById(R.id.contactThumbnail);
		//mImageView.setImageBitmap(getBitmapFromURL("http://4.bp.blogspot.com/-JOqxgp-ZWe0/U3BtyEQlEiI/AAAAAAAAOfg/Doq6Q2MwIKA/s1600/google-logo-874x288.png"));

		// Calling async task to get json
		new GetContacts().execute();
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					contacts = jsonObj.getJSONArray(TAG_CONTACTS);

					// looping through All Contacts
					for (int i = 0; i < contacts.length(); i++) {
						JSONObject c = contacts.getJSONObject(i);

						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);
						String email = c.getString(TAG_EMAIL);
						String address = c.getString(TAG_ADDRESS);
						String mobile = c.getString(TAG_MOBILE);
						String imglink = c.getString(TAG_IMG_LINK);

						// tmp hashmap for single contact
						HashMap<String, String> contact = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						contact.put(TAG_ID, id);
						contact.put(TAG_NAME, name);
						contact.put(TAG_EMAIL, email);
						contact.put(TAG_MOBILE, mobile);
						contact.put(TAG_IMG_LINK, imglink);

						//mImageView = (ImageView) findViewById(R.id.contactThumbnail);
						//mImageView
								//.setImageBitmap(getBitmapFromURL("http://4.bp.blogspot.com/-JOqxgp-ZWe0/U3BtyEQlEiI/AAAAAAAAOfg/Doq6Q2MwIKA/s1600/google-logo-874x288.png"));
						//mImageView.setImageBitmap(bm);(getResources(), R.drawable.dale);
						//mImageView.setImageURI("http://4.bp.blogspot.com/-JOqxgp-ZWe0/U3BtyEQlEiI/AAAAAAAAOfg/Doq6Q2MwIKA/s1600/google-logo-874x288.png");
						// adding contact to contact list
						contactList.add(contact);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			ListAdapter adapter = new SimpleAdapter(MainActivity.this,
					contactList, R.layout.list_item, new String[] { TAG_NAME,
							TAG_EMAIL, TAG_MOBILE }, new int[] { R.id.name,
							R.id.email, R.id.mobile });

			setListAdapter(adapter);
		}

	}

//	public void loadBitmap(View view) {
//		Bitmap b = getBitmapFromURL("http://upload.wikimedia.org/wikipedia/en/7/70/Example.png");
//	}
//
//	public static Bitmap getBitmapFromURL(String src) {
//	    try {
//	        URL url = new URL(src);
//	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//	        connection.setDoInput(true);
//	        connection.connect();
//	        InputStream input = connection.getInputStream();
//	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
//	        return myBitmap;
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        return null;
//	    }
//	}

}