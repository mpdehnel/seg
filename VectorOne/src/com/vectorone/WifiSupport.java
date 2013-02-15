package com.vectorone;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiSupport extends BroadcastReceiver {

	private WifiManager wifi;
	private WifiInfo info;
	private String TAG = "WIFI";

	public WifiSupport() {
		// TODO Auto-generated constructor stub
	}

	public String getMacAddress(Context context) {
		wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		info = wifi.getConnectionInfo();
		Log.i(TAG, "BSSID:" + info.getBSSID());
		Log.i(TAG, "MAC:" + info.getMacAddress());
		Log.i(TAG, "SSID:" + info.getSSID());
		Log.i(TAG, "info" + info.toString());
		return info.getBSSID();
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager conMan = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMan.getActiveNetworkInfo();
	
		if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			Log.i(TAG, "Have Wifi Connection");
			getMacAddress(context);
		} else {
			Log.i(TAG, "Don't have Wifi Connection");
		}
	}

}