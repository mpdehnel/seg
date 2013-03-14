package com.vectorone;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.data.Cache;
import com.data.DataClass;
import com.data.Model;
import com.data.MyHttpClient;
import com.findCache.MapsActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CacheShowActivity extends Activity {

	private RelativeLayout relativlayout;
	private TextView CacheName;
	private TextView CacheDiscription;
	private TextView question;
	private Button yes_with_routing;
	private Button yes_Button;
	private Button no_Button;
	private int cacheindex;
	private boolean frommap;
	private Button rate_button;
	private RatingBar ratebar;
	private List<Model> listofsortetcaches;
	private Vibrator vibrator;
	private Button comment;
	private TextView report;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cachshow);
		Intent intent = getIntent();
		// TODO: mapsboolen handel
		cacheindex = intent.getIntExtra("CacheIndex", -1);
		frommap = intent.getBooleanExtra("frommaps", false);
		listofsortetcaches = DataClass.getcacheswithfilter();
		initfields();
		handelfrommap();
		setupListener();
		setupfont();
		setupbackgroundimage();
		Cache cache = listofsortetcaches.get(cacheindex).getCach();
		CacheName.setText(cache.getName());
		CacheDiscription.setText(cache.getDescripton());
		CacheDiscription.setScrollbarFadingEnabled(false);
		CacheDiscription.setVerticalFadingEdgeEnabled(false);

	}

	private void handelfrommap() {
		if (frommap) {
			no_Button.setVisibility(Button.INVISIBLE);
			yes_Button.setText("Back to Map");
			question.setVisibility(TextView.INVISIBLE);
			yes_with_routing.setVisibility(Button.INVISIBLE);
		} else {
			no_Button.setVisibility(Button.VISIBLE);
			question.setVisibility(TextView.VISIBLE);
			yes_with_routing.setVisibility(Button.VISIBLE);
			yes_Button.setText("yes");
			yes_with_routing.setText("Route");
		}
	}

	private void initfields() {
		relativlayout = (RelativeLayout) findViewById(R.id.cacheshowlayout);
		CacheName = (TextView) findViewById(R.id.CachName);
		CacheDiscription = (TextView) findViewById(R.id.CacheDiscription);
		yes_Button = (Button) findViewById(R.id.yes_button);
		yes_with_routing = (Button) findViewById(R.id.routing_button);
		no_Button = (Button) findViewById(R.id.no_button);
		rate_button = (Button) findViewById(R.id.ratethisCache);
		question = (TextView) findViewById(R.id.doyouwant);
		ratebar = (RatingBar) findViewById(R.id.Cacheratingbar);
		comment = (Button) findViewById(R.id.leaveAcomment);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		report=(TextView) findViewById(R.id.report);
		CacheDiscription.setMovementMethod(ScrollingMovementMethod
				.getInstance());

	}

	private void setupListener() {
		yes_with_routing.setOnClickListener(clickhandler);
		yes_Button.setOnClickListener(clickhandler);
		no_Button.setOnClickListener(clickhandler);
		rate_button.setOnClickListener(clickhandler);
		comment.setOnClickListener(clickhandler);
		report.setOnClickListener(clickhandler);
	}

	private void setupbackgroundimage() {
		relativlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonsmall);
		Drawable buttonimageinaktiv = getResources().getDrawable(
				R.drawable.buttonmediumgrey);
		report.setBackgroundDrawable(buttonimage);
		yes_Button.setBackgroundDrawable(buttonimage);
		yes_with_routing.setBackgroundDrawable(buttonimage);
		no_Button.setBackgroundDrawable(buttonimage);
		comment.setBackgroundDrawable(buttonimage);
		if (listofsortetcaches.get(cacheindex).getCach().isFound()&&!listofsortetcaches.get(cacheindex).getCach().israted()) {
			rate_button.setBackgroundDrawable(buttonimage);

			ratebar.setEnabled(!listofsortetcaches.get(cacheindex).getCach()
					.israted());

		} else {
			rate_button.setBackgroundDrawable(buttonimageinaktiv);
			ratebar.setEnabled(false);
		}
	}

	private void setupfont() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#45250F");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 22;
		CacheName.setTypeface(font);
		CacheName.setTextColor(textcolor);
		CacheName.setTextSize(textsize);

		CacheDiscription.setTypeface(font);
		CacheDiscription.setTextColor(textcolor);
		CacheDiscription.setTextSize(textsize);
		
		question.setTypeface(font);
		question.setTextSize(textsize);
		question.setTextColor(textcolor);

		yes_Button.setTypeface(font);
		yes_Button.setTextSize(textsize);
		yes_Button.setTextColor(buttoncolor);

		comment.setTypeface(font);
		comment.setTextSize(textsize);
		comment.setTextColor(buttoncolor);

		no_Button.setTypeface(font);
		no_Button.setTextSize(textsize);
		no_Button.setTextColor(buttoncolor);

		rate_button.setTypeface(font);
		rate_button.setTextSize(textsize);
		rate_button.setTextColor(buttoncolor);

		yes_with_routing.setTypeface(font);
		yes_with_routing.setTextSize(textsize);
		yes_with_routing.setTextColor(buttoncolor);

		
		report.setTypeface(font);
		report.setTextSize(textsize);
		report.setTextColor(buttoncolor);
		// yes_with_routing.setVisibility(Button.INVISIBLE);

	}

	private void clickhandle(View v) {
		vibrator.vibrate(50);
		if (v == comment) {
			finish();
			Intent intent = new Intent(getApplicationContext(),
					CommentActivity.class);
			intent.putExtra("Cacheid", listofsortetcaches.get(cacheindex)
					.getCach().get_id());
			intent.putExtra("CacheIndex", cacheindex);
			startActivity(intent);
		}

		if (v == no_Button) {

			finish();
			listofsortetcaches.get(cacheindex).setSelected(false);
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));

		}
		if (v == yes_Button) {
			if (frommap) {
				finish();
				startActivity(new Intent(getApplicationContext(),
						MapsActivity.class));

			} else {
				finish();
				listofsortetcaches.get(cacheindex).setSelected(true);
				startActivity(new Intent(getApplicationContext(),
						CacheSelectActivity.class));
			}
		}
		if (v == yes_with_routing) {
			finish();
			listofsortetcaches.get(cacheindex).setSelected(true);
			DataClass.routingpoint = listofsortetcaches.get(cacheindex)
					.getCach().getGeopoint();
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));
		}
		if(v==report){
			MyHttpClient client=new MyHttpClient(DataClass.server);
			try {
				Toast.makeText(getBaseContext(), client.sendReport(listofsortetcaches.get(cacheindex).getCach().get_id()),Toast.LENGTH_LONG).show();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (v == rate_button) {
			float rating = ratebar.getRating();
			MyHttpClient http = new MyHttpClient(DataClass.server);
			Cache cache = listofsortetcaches.get(cacheindex).getCach();
			if (cache.isFound() && !cache.israted()) {
				Toast.makeText(getApplicationContext(), "Rated: " + rating,
						Toast.LENGTH_SHORT).show();
				try {
					Toast.makeText(getApplicationContext(),
							http.pushrating(cache.get_id(), rating),
							Toast.LENGTH_SHORT).show();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(cache.isFound() && cache.israted()){
				Toast.makeText(getApplicationContext(),
						"Has been rated",
						Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getApplicationContext(),
						"You have to find the cache befor you could rate it!",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onBackPressed() {
		if (!frommap) {
			finish();
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));
		} else {
			finish();
			startActivity(new Intent(getApplicationContext(),
					MapsActivity.class));
		}
	}

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clickhandle(v);
		}

	};

}
