package com.data;

import java.util.LinkedList;
import java.util.List;

import com.game.keepopen.Game_keepopen_Activity;
import com.game.memory.Game_memory_Activity;
import com.vectorone.CacheShowActivity;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.ContactsContract.Contacts.Data;
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
		if (info != null) {
			Log.i(TAG, "BSSID:" + info.getBSSID());
			Log.i(TAG, "MAC:" + info.getMacAddress());
			Log.i(TAG, "SSID:" + info.getSSID());
			Log.i(TAG, "info" + info.toString());
			return info.getBSSID();

		}
		return "no WIFI";
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		
		ConnectivityManager conMan = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMan.getActiveNetworkInfo();
		if (netInfo != null && conMan != null) {
			if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				Log.i(TAG, "Have Wifi Connection BSSID="
						+ getMacAddress(context));
				checkWIFICaches(context, getMacAddress(context));
			} else {
				Log.i(TAG, "Don't have Wifi Connection");
			}
		}
	}

	public static void checkWIFICaches(Context context, String Macaddress) {

		for (int i = 0; i < DataClass.selectedCaches.size(); i++) {
			Cache selectedCach = DataClass.selectedCaches.get(i);

			Log.i("DATACLASS", selectedCach.getMacAdd() + "-Macaddess-"
					+ Macaddress);
			if (context != null) {
				if (Macaddress.length() == 17
						&& selectedCach.getMacAdd().length() == 17) {
					Log.i("DATACLASS", selectedCach.getMacAdd() + "-Macaddess-"
							+ Macaddress);
					if (!selectedCach.isFound()) {
						Log.i("DATACLASS", selectedCach.getMacAdd() + "-Macaddess-"
								+ Macaddress);
						if (selectedCach.getMacAdd().equalsIgnoreCase(Macaddress)) {
							Log.i("DATACLASS", selectedCach.getMacAdd() + "-Macaddess-"
									+ Macaddress);
							int choice = (int) (Math.random() * 2);
							DataClass.log.append("WifiCache found:"+DataClass.selectedCaches.get(i).getName());
							if (choice == 0) {
								DataClass.log.append("Game: KeepOpen");
								Intent intent = new Intent(context,
										Game_keepopen_Activity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								context.startActivity(intent);
							} else {
								DataClass.log.append("Game: Memory");
								Intent intent = new Intent(context,
										Game_memory_Activity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								context.startActivity(intent);
							}
						}
					}
				}
			}
		}
	}

}