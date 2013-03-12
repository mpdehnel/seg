package com.findCache;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.data.DataClass;
import com.data.Model;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.vectorone.CacheShowActivity;
import com.vectorone.MeActivity;

public class ItemOverlay extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;

	public ItemOverlay(Drawable defaultMarker) {
		super(defaultMarker);
		// TODO Auto-generated constructor stub
	}

	public ItemOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	public void removeOverlay(OverlayItem overlay) {
		mOverlays.remove(overlay);
		populate();
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
		/*AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();*/
		if(item.getTitle().equals("Hi")){
			Intent intent = new Intent(mContext,
					MeActivity.class);
			intent.putExtra("frommaps", true);
			mContext.startActivity(intent);

			return true;
		}else{
			for(int i=0;i<DataClass.caches.size();i++){
				List<Model> listofsortetcaches = DataClass.getcacheswithfilter();
				if(item.getTitle().equals(listofsortetcaches.get(i).getCach().getName())){
					index=i;
				}
			}
		Intent intent = new Intent(mContext,
				CacheShowActivity.class);
		intent.putExtra("CacheIndex", index);
		intent.putExtra("frommaps", true);
		mContext.startActivity(intent);
		return true;
		}
	}

}