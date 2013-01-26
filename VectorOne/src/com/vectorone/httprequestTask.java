package com.vectorone;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.data.DataClass;
import com.data.MyHttpClient;

import android.os.AsyncTask;
import android.provider.ContactsContract.Contacts.Data;



public class httprequestTask extends AsyncTask<String, String, String>{

	@Override
	protected String doInBackground(String... params) {
		try {
			DataClass.addCachesFromDataBase(new MyHttpClient("http://www.netroware.co.uk/test/").getCachesfromDatabase("asd"));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}