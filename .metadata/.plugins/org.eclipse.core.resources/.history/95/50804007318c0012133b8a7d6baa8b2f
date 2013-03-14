package com.vectorone;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.params.HttpAbstractParamBean;

import com.data.DataClass;
import com.data.MyHttpClient;

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
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends Activity {

	private RelativeLayout relativlayout;
	private Button send;
	private Button cancel;
	private EditText comment;
	private Vibrator vibrator;
	private int cacheid;
	private int cacheindex;
	private TextView lable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_comment);
		Intent intent = getIntent();
		cacheindex = intent.getIntExtra("CacheIndex", -1);
		cacheid = intent.getIntExtra("Cacheid", -1);
		initfields();
		setupListener();
		setupfont();
		setupbackgroundimage();
	}

	private void setupbackgroundimage() {
		relativlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));

		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonsmall);
		Drawable textfieldimage = getResources().getDrawable(
				R.drawable.textentrybig);
		send.setBackgroundDrawable(buttonimage);
		cancel.setBackgroundDrawable(buttonimage);
		comment.setBackgroundDrawable(textfieldimage);
	}

	private void setupfont() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#DECD87");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 17;

		send.setTypeface(font);
		send.setTextColor(buttoncolor);
		send.setTextSize(textsize);

		cancel.setTypeface(font);
		cancel.setTextColor(buttoncolor);
		cancel.setTextSize(textsize);

		comment.setTypeface(font);
		comment.setTextColor(textcolor);
		comment.setTextSize(textsize);

		lable.setTypeface(font);
		lable.setTextColor(textcolor);
		lable.setTextSize(textsize);

	}

	private void setupListener() {
		send.setOnClickListener(clickhandler);
		cancel.setOnClickListener(clickhandler);
	}

	private void initfields() {
		relativlayout = (RelativeLayout) findViewById(R.id.commentlayout);
		send = (Button) findViewById(R.id.pushcomment);
		cancel = (Button) findViewById(R.id.backcomment);
		comment = (EditText) findViewById(R.id.comment);
		lable=(TextView) findViewById(R.id.lablecomment);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	}

	private OnClickListener clickhandler = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == send) {
				String commet = comment.getText().toString();
				if (commet.length() > 1) {
					MyHttpClient client = new MyHttpClient(DataClass.server);
					try {
						Toast.makeText(
								getBaseContext(),
								client.pushComment(
										DataClass.user.getUsername(), cacheid,
										commet), Toast.LENGTH_LONG).show();
					} catch (ClientProtocolException e) {
						Toast.makeText(getBaseContext(), "internet issus",
								Toast.LENGTH_LONG).show();
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (v == cancel) {
				onBackPressed();
			}

		}
	};

	@Override
	public void onBackPressed() {
		finish();
		Intent intent = new Intent(getBaseContext(), CacheShowActivity.class);
		intent.putExtra("CacheIndex", cacheindex);
		startActivity(intent);
	};
}
